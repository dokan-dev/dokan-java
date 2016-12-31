package com.dokany.java.examples.memoryfs;

import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NORMAL;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyException;
import com.dokany.java.FileSystem;
import com.dokany.java.Utils;
import com.dokany.java.Win32FindStreamData;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.MountError;
import com.dokany.java.structure.ByHandleFileInfo;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

import jetbrains.exodus.ArrayByteIterable;
import jetbrains.exodus.ByteIterable;
import jetbrains.exodus.bindings.StringBinding;
import jetbrains.exodus.core.dataStructures.hash.HashSet;
import jetbrains.exodus.env.Environment;
import jetbrains.exodus.env.Environments;
import jetbrains.exodus.env.Store;
import jetbrains.exodus.env.StoreConfig;
import jetbrains.exodus.env.Transaction;
import jetbrains.exodus.vfs.File;
import jetbrains.exodus.vfs.VfsInputStream;
import jetbrains.exodus.vfs.VirtualFileSystem;

public class MemoryFS implements FileSystem {

	private final static String ROOT_PATH = "/";
	private final static Logger LOGGER = LoggerFactory.getLogger(MemoryFS.class);

	/**
	 * Path matcher glob
	 */
	private final static String GLOB = "glob:";
	private final static java.nio.file.FileSystem DEFAULT_FS = FileSystems.getDefault();

	// File size
	private final static long INITIAL_USED_BYTES = 1024L * 1024L;
	private final static long INITIAL_TOTAL_BYTES = 256L * INITIAL_USED_BYTES;

	// VFS - virtual file store provided by xodus
	private Environment env;
	private VirtualFileSystem vfs;
	private Store infoStore;

	public MemoryFS() {
	}

	@Override
	public void mounted() {
		try {
			// Try to create store location in temp directory
			final Path fileStorePath = Files.createTempDirectory("dokany-java_");

			// Init VFS
			env = Environments.newInstance(fileStorePath.toString());
			vfs = new VirtualFileSystem(env);

			// File store name
			final String fileStoreName = "com.dokany.java.fileinfo";

			// Create/open store and save in fileInfoStore
			infoStore = env.computeInTransaction((@NotNull final Transaction txn) -> env.openStore(fileStoreName, StoreConfig.WITHOUT_DUPLICATES, txn));

			createSampleItems();
		} catch (final IOException e) {
			throw new DokanyException(MountError.DOKAN_START_ERROR.val);
		}
	}

	@Override
	public void unmounted() {

		vfs.shutdown();
		env.close();
	}

	/**
	 * Determines if file exists already or not. Calls {@link #getExistingFile(String)}.
	 *
	 * @param path
	 */
	@Override
	public boolean pathExists(final String path) {
		return Utils.isNotNull(getExistingFile(path));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	public File getExistingFile(final String path) {
		LOGGER.trace("getExistingFile: {}", path);
		// will be null if openFile does not find it
		return env.computeInTransaction((@NotNull final Transaction txn) -> getExistingFile(path, txn));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	private File getExistingFile(final String path, final Transaction txn) {
		return vfs.openFile(txn, path, false);
	}

	/**
	 * Throws an exception if file cannot be found for specified path.
	 *
	 * @param path
	 * @return file or FileNotFoundException
	 * @throws FileNotFoundException
	 */
	private File getExistingFileWithException(final String path, final Transaction txn) throws FileNotFoundException {
		final File file = getExistingFile(path, txn);
		if (Utils.isNull(file)) {
			throw new FileNotFoundException("Could not find file for path: " + path);
		}
		return file;
	}

	@Override
	public Set<WIN32_FIND_DATA> findFiles(final String pathToSearch) {
		return findFilesWithPattern(pathToSearch, null);
	}

	@Override
	public Set<WIN32_FIND_DATA> findFilesWithPattern(final String pathToSearch, final String pattern) {
		final Set<WIN32_FIND_DATA> files = new HashSet<>();

		// Only use if pattern is not null
		final PathMatcher pathMatcher = Utils.isNotNull(pattern) ? DEFAULT_FS.getPathMatcher(GLOB + ROOT_PATH + pattern) : null;

		env.executeInTransaction((@NotNull final Transaction txn) -> {
			vfs.getFiles(txn).forEach(file -> {
				final String path = file.getPath();
				if (isPathMatch(pathToSearch, pattern, pathMatcher)) {
					files.add(getInfo(path, txn).toWin32FindData());
				}
			});
		});
		return files;
	}

	public boolean isPathMatch(final String pathToSearch, final String pattern, final PathMatcher pathMatcher) {
		boolean isMatch = false;

		LOGGER.debug("isPathMatch:  pathToSearch = {}; pattern = {}", pathToSearch, pattern);

		// Do not match root or it will get listed in the directory
		if (!pathToSearch.equals(ROOT_PATH)) {
			if (Utils.isNotNull(pattern)) {
				// findFilesWithPattern
				isMatch = pathMatcher.matches(Paths.get(pathToSearch));
			} else {
				// findFiles
				isMatch = pathToSearch.startsWith(pathToSearch);
			}
		}
		return isMatch;
	}

	@Override
	public Set<Win32FindStreamData> findStreams(final String path) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void deleteFile(final String path) throws IOException {
		delete(path);
	}

	@Override
	public void deleteDirectory(final String path) throws IOException {
		delete(path);
	}

	private void delete(final String path) {
		env.executeInTransaction((@NotNull final Transaction txn) -> {
			vfs.deleteFile(txn, path);
		});
	}

	/**
	 *
	 */
	@Override
	public void move(final String oldPath, final String newPath, final boolean replaceIfExisting) throws IOException {
		throw new UnsupportedOperationException("Not yet implemented");

		/*
		 * env.executeInTransaction((@NotNull final Transaction txn) -> { final VfsInputStream inputStream = vfs.readFile(txn, descriptor); inputStream.read(data, offset,
		 * Math.min(length, data.length - offset)); }); findExisting(oldPath, oldPath.isDirectory()).replaceWith(findExisting(newPath, newPath.isDirectory()));
		 */
	}

	/**
	 * This method creates ByHandleFileInfo for the specified file and attributes. All times are automatically set to now. Size is automatically set to 0.
	 *
	 * @param file
	 * @param attributes
	 * @return
	 */
	private ByHandleFileInfo getNewInfo(final File file, final FileAttribute attributes) {
		return new ByHandleFileInfo.Builder(file.getPath())
		        .attributes(attributes)
		        .volumeSerialNumber(getVolumeSerialNumber())
		        .build();
	}

	/**
	 * This method retrieves ByHandleFileInfo from the VFS.
	 */
	@Override
	public ByHandleFileInfo getInfo(final String path) {
		return env.computeInTransaction((@NotNull final Transaction txn) -> {
			return getInfo(path, txn);
		});
	}

	/**
	 * This method retrieves ByHandleFileInfo from the VFS.
	 */
	private ByHandleFileInfo getInfo(final String path, final Transaction txn) {
		final ArrayByteIterable pathKey = StringBinding.stringToEntry(path);
		final ByteIterable iterable = infoStore.get(txn, pathKey);
		return new ByHandleFileInfo(path, iterable);
	}

	/**
	 * Creates a new empty file for the path and sets the specified attributes.
	 */
	@Override
	public void createEmptyFile(final String path, final long options, final FileAttribute attributes) {
		env.executeInTransaction((@NotNull final Transaction txn) -> {
			createEmptyFile(path, options, attributes, txn);
		});
	}

	/**
	 * Creates a new empty file for the path and sets the specified attributes.
	 */
	private void createEmptyFile(final String path, final long options, final FileAttribute attributes, final Transaction txn) {
		final File file = createFile(path, txn);
		final ByHandleFileInfo info = getNewInfo(file, attributes);
		setInfo(path, info, txn);
	}

	/**
	 * Creates a new empty directory for the path and sets the specified attributes.
	 */
	@Override
	public void createEmptyDirectory(final String path, final long options, final FileAttribute attributes) {
		createEmptyFile(path, options, attributes);
	}

	/**
	 * Calls {@link jetbrains.exodus.vfs.VirtualFileSystem#createFile(Transaction, String)}
	 */
	private File createFile(final String path, final Transaction txn) {
		final String updatedPath;
		if (!path.startsWith(ROOT_PATH)) {
			updatedPath = ROOT_PATH + path;
		} else {
			updatedPath = path;
		}
		return vfs.createFile(txn, updatedPath);
	}

	/**
	 * Reads file from VFS and stores into data array.
	 */
	@Override
	public int read(final String path, final int offset, final byte[] data, final int readLength) throws IOException {
		if (readLength < 1) {
			throw new IOException("readLength cannot be empty; file should not be read if it is empty");
		}

		if (Utils.isNull(path)) {
			throw new FileNotFoundException("path was null");
		}

		final int numRead = env.computeInTransaction((@NotNull final Transaction txn) -> {
			int toReturn = 0;

			final File file = getExistingFile(path, txn);
			final long fileSize = vfs.getFileLength(txn, file);

			if (fileSize > 0) {
				if (Utils.isNotNull(file)) {
					final VfsInputStream inputStream = vfs.readFile(txn, file);
					try {
						toReturn = inputStream.read(data, offset, Math.min(readLength, data.length - offset));
					} catch (final IOException e) {
						LOGGER.warn("Read fault on path {}", path, e);
						toReturn = -1;
					}
				}
			}
			return toReturn;
		});

		if (numRead == -1) {
			throw new IOException("Error reading file");
		}
		return numRead;
	}

	/**
	 * Writes a file or directory to VFS.
	 *
	 * @param path
	 * @param offset
	 * @param data
	 * @param int
	 * @param info
	 */
	private int writeAll(final File file, final byte[] data, final Transaction txn) throws IOException {
		return write(file, 0, data, data.length, null, txn);
	}

	/**
	 * Writes a file or directory to VFS.
	 *
	 * @param path
	 * @param offset
	 * @param data
	 * @param int
	 * @param info
	 */
	@Override
	public int write(final String path, final int offset, final byte[] data, final int writeLength) throws IOException {
		if (Utils.isNull(path)) {
			throw new FileNotFoundException("Path was null");
		}

		final IOException error = env.computeInTransaction((@NotNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				final File file = getExistingFileWithException(path, txn);
				write(file, offset, data, writeLength, null, txn);
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Utils.isNotNull(error)) {
			throw error;
		}

		return writeLength;
	}

	private int write(final File file, final int offset, final byte[] data, final int writeLength, final ByHandleFileInfo info, final Transaction txn) throws IOException {
		try (DataOutputStream output = new DataOutputStream(vfs.writeFile(txn, file))) {
			output.write(data, offset, writeLength);
		}

		// This has to go outside the try statement so the buffer is fully written/flushed
		final long fileSize = vfs.getFileLength(txn, file);
		LOGGER.debug("wrote file: {}", file.getPath());

		ByHandleFileInfo infoToSet = info;
		if (Utils.isNull(info)) {
			infoToSet = new ByHandleFileInfo.Builder(file.getPath())

			        // TODO: attribute may not be correct
			        // Find way to determine if this is a directory
			        .attributes(FileAttribute.NORMAL)
			        .volumeSerialNumber(getVolumeSerialNumber())
			        .size(fileSize)
			        .creationTime(file.getCreated())
			        .build();
		}
		setInfo(file.getPath(), infoToSet, txn);
		return writeLength;
	}

	/**
	 * Stores ByHandleFileInfo into VFS.
	 *
	 * @param path
	 * @param info
	 */
	private void setInfo(final String path, final ByHandleFileInfo info, final Transaction txn) {
		final ArrayByteIterable pathKey = StringBinding.stringToEntry(path);
		LOGGER.debug("set info for {} to {}", path, info);
		infoStore.put(txn, pathKey, info.toByteIterable());
	}

	/**
	 * Stores ByHandleFileInfo into VFS.
	 *
	 * @param path
	 * @param info
	 */
	private void setInfo(final String path, final ByHandleFileInfo info) {
		env.executeInTransaction((@NotNull final Transaction txn) -> {
			setInfo(path, info, txn);
		});
	}

	@Override
	/**
	 * Sets attributes on path.
	 */
	public void setAttributes(final String path, final FileAttribute attributes) {
		int toSet = FILE_ATTRIBUTE_NORMAL;
		// Will be null if coming from findExisting method
		if (Utils.isNotNull(attributes)) {
			toSet = attributes.val;
		}

		final ByHandleFileInfo info = getInfo(path);
		info.dwFileAttributes = toSet;
		setInfo(path, info);
	}

	@Override
	public void setTime(final String path, final FILETIME creation, final FILETIME lastAccess, final FILETIME lastModification) {
		final ByHandleFileInfo info = getInfo(path);
		info.ftCreationTime = creation;
		info.ftLastAccessTime = lastAccess;
		info.ftLastWriteTime = lastModification;
		setInfo(path, info);
	}

	@Override
	public void setSecurity(final String path, final int kind, final byte[] data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unlock(final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Unlocking not implemented.");
	}

	@Override
	public void lock(final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Locking not implemented.");
	}

	@Override
	public long truncate(final String path) throws IOException {
		// final Node parent = item.getParent();

		/*
		 * vfs. final ByHandleFileInfo fileInfo = handle.getFileInfo(); final ByHandleFileInfo parentFileInfo = createHandle(parent.getPath(), parent).getFileInfo(); final FILETIME
		 * now = new FILETIME(new Date()); fileInfo.ftLastAccessTime = now; parentFileInfo.ftLastAccessTime = now; fileInfo.ftLastWriteTime = now; parentFileInfo.ftLastWriteTime =
		 * now; return ErrorCodes.ERROR_ALREADY_EXISTS.val;
		 */
		// TODO: complete
		return 0;
	}

	@Override
	public void setAllocationSize(final String path, final int length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEndOfFile(final String path, final int offset) {
		// TODO Auto-generated method stub
	}

	@Override
	public void flushFileBuffers(final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup(final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void close(final String path) {
		// TODO Auto-generated method stub
	}

	/**
	 * Default is VOLUME;
	 *
	 * @return
	 */
	@Override
	public String getVolumeName() {
		return "EXAMPLE_FS";
	}

	/**
	 * Default is VOLUME;
	 *
	 * @return
	 */
	@Override
	public int getVolumeSerialNumber() {
		return 200000;
	}

	/**
	 * Default is DOKANY.
	 *
	 * @return
	 */
	@Override
	public String getFileSystemName() {
		return "Dokany ExampleFS";
	}

	@Override
	public long getTotalBytesAvailable() {
		// TODO: Update after initial set
		return INITIAL_TOTAL_BYTES;
	}

	@Override
	public long getUsedBytes() {
		// TODO: Update after initial set
		return INITIAL_USED_BYTES;
	}

	@Override
	public boolean isDebug() {
		return true;
	}

	@Override
	public boolean isDebugStderrOutput() {
		return true;
	}

	@Override
	public final String getRootPath() {
		return ROOT_PATH;
	}

	/**
	 * // Dates will be automatically set to now by FileInfoBuilder. Number of links will default to 1.
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	private void createSampleItems() throws IOException {
		final ByHandleFileInfo info = new ByHandleFileInfo.Builder(ROOT_PATH)
		        .size(getTotalBytesAvailable())
		        .attributes(FileAttribute.DEVICE)
		        .volumeSerialNumber(getVolumeSerialNumber())
		        .build();

		final IOException error = env.computeInTransaction((@NotNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				// Root - must be created
				createFile(ROOT_PATH, txn);
				setInfo(ROOT_PATH, info, txn);

				// File 1 - empty
				createEmptyFile(ROOT_PATH + "1.txt", 0, FileAttribute.NORMAL, txn);

				// File 2 - 5 bytes
				File file = createFile(ROOT_PATH + "2.txt", txn);
				writeAll(file, new byte[] { 'H', 'E', 'L', 'L', 'O' }, txn);

				// Directory - empty
				createEmptyFile(ROOT_PATH + "testFolder", 0, FileAttribute.DIRECTORY, txn);

				// File 3 - several bytes
				file = createFile("testFolder/3.TXT", txn);
				writeAll(file, "This file is within testFolder".getBytes(StandardCharsets.UTF_8), txn);
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Utils.isNotNull(error)) {
			throw error;
		}
	}
}
