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
import java.util.Date;
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
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.FullFileInfo;
import com.dokany.java.structure.VolumeInformation;
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

public class MemoryFS extends FileSystem {

	private final static Logger LOGGER = LoggerFactory.getLogger(MemoryFS.class);

	/**
	 * Path matcher glob
	 */
	private final static String GLOB = "glob:";
	private final static java.nio.file.FileSystem DEFAULT_FS = FileSystems.getDefault();

	// VFS - virtual file store provided by xodus
	private final Environment env;
	private final VirtualFileSystem vfs;
	private final Store infoStore;

	public MemoryFS(final DeviceOptions deviceOptions) throws IOException {
		super(deviceOptions, new VolumeInformation(), new FreeSpace(1024L * 1024L * 256L, 1024L * 1024L), new Date(), "/");

		// Try to create store location in temp directory
		final Path fileStorePath = Files.createTempDirectory("dokany-java_");

		// Init VFS
		env = Environments.newInstance(fileStorePath.toString());
		vfs = new VirtualFileSystem(env);

		// File store name
		final String fileStoreName = "com.dokany.java.fileinfo";

		// Create/open store and save in fileInfoStore
		// must not be read only so that store can be created
		infoStore = env.computeInTransaction((@NotNull final Transaction txn) -> env.openStore(fileStoreName, StoreConfig.WITHOUT_DUPLICATES, txn));

	}

	@Override
	public void mounted() {
		try {
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
	public boolean doesPathExist(@NotNull final String path) {
		return Utils.isNotNull(getExistingFile(path));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	public File getExistingFile(@NotNull final String path) {
		LOGGER.trace("getExistingFile: {}", path);
		// will be null if openFile does not find it
		return env.computeInReadonlyTransaction((@NotNull final Transaction txn) -> getExistingFile(path, txn));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	private File getExistingFile(@NotNull final String path, @NotNull final Transaction txn) {
		return vfs.openFile(txn, path, false);
	}

	/**
	 * Throws an exception if file cannot be found for specified path.
	 *
	 * @param path
	 * @return file or FileNotFoundException
	 * @throws FileNotFoundException
	 */
	private File getExistingFileWithException(@NotNull final String path, @NotNull final Transaction txn) throws FileNotFoundException {
		final File file = getExistingFile(path, txn);
		if (Utils.isNull(file)) {
			throw new FileNotFoundException("Could not find file for path: " + path);
		}
		return file;
	}

	@Override
	public Set<WIN32_FIND_DATA> findFiles(@NotNull final String pathToSearch) {
		final Set<WIN32_FIND_DATA> files = new HashSet<>();

		env.executeInReadonlyTransaction((@NotNull final Transaction txn) -> {
			vfs.getFiles(txn).forEach(file -> {
				final String path = file.getPath();

				// Do not match root or it will get listed in the directory
				if (!pathToSearch.equals(rootPath) && path.startsWith(pathToSearch)) {
					try {
						files.add(getInfo(path, txn).toWin32FindData());
					} catch (final FileNotFoundException e) {
						LOGGER.warn("Failed to add found file because of caught exception", e);
					}
				}
			});
		});
		return files;
	}

	@Override
	public Set<WIN32_FIND_DATA> findFilesWithPattern(@NotNull final String pathToSearch, @NotNull final String pattern) {
		final Set<WIN32_FIND_DATA> files = new HashSet<>();

		LOGGER.debug("findFilesWithPattern memoryfs:   path  {};     pattern {}", pathToSearch, pattern);
		// Only use if pattern is not null
		final PathMatcher pathMatcher = DEFAULT_FS.getPathMatcher(GLOB + rootPath + pattern);

		env.executeInReadonlyTransaction((@NotNull final Transaction txn) -> {
			vfs.getFiles(txn).forEach(file -> {
				final String path = file.getPath();
				// Do not match root or it will get listed in the directory
				if (!pathToSearch.equals(rootPath) && pathMatcher.matches(Paths.get(pathToSearch))) {
					try {
						files.add(getInfo(path, txn).toWin32FindData());
					} catch (final FileNotFoundException e) {
						LOGGER.warn("Failed to add found file because of caught exception", e);
					}
				}
			});
		});
		return files;
	}

	@Override
	public Set<Win32FindStreamData> findStreams(final String path) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void deleteFile(@NotNull final String path) throws IOException {
		delete(path);
	}

	@Override
	public void deleteDirectory(@NotNull final String path) throws IOException {
		delete(path);
	}

	private void delete(@NotNull final String path) {
		env.executeInTransaction((@NotNull final Transaction txn) -> {
			vfs.deleteFile(txn, path);
		});
	}

	/**
	 *
	 */
	@Override
	public void move(@NotNull final String oldPath, @NotNull final String newPath, final boolean replaceIfExisting) throws IOException {
		throw new UnsupportedOperationException("Not yet implemented");

		/*
		 * env.executeInTransaction((@NotNull final Transaction txn) -> { final VfsInputStream inputStream = vfs.readFile(txn, descriptor); inputStream.read(data, offset,
		 * Math.min(length, data.length - offset)); }); findExisting(oldPath, oldPath.isDirectory()).replaceWith(findExisting(newPath, newPath.isDirectory()));
		 */
	}

	/**
	 * This method creates FullFileInfo for the specified file and attributes. All times are automatically set to now. Size is automatically set to 0.
	 *
	 * @param file
	 * @param attributes
	 * @return
	 * @throws FileNotFoundException
	 */
	private FullFileInfo getNewInfo(@NotNull final File file, final FileAttribute attributes) throws FileNotFoundException {
		return new FullFileInfo(file.getPath(), file.getDescriptor(), attributes, getVolumeInfo().getVolumeSerialNumber());
	}

	/**
	 * This method retrieves FullFileInfo from the VFS.
	 *
	 * @throws IOException
	 */
	@Override
	public FullFileInfo getInfo(@NotNull final String path) throws IOException {
		LOGGER.debug("getInfo for {}", path);

		final FullFileInfo result = env.computeInReadonlyTransaction((@NotNull final Transaction txn) -> {
			FullFileInfo toReturn = null;
			try {
				toReturn = getInfo(path, txn);
			} catch (final IOException e) {
				LOGGER.warn("Could not retrieve info", e);
			}
			return toReturn;
		});

		if (Utils.isNull(result)) {
			throw new IOException();
		}
		return result;
	}

	/**
	 * This method retrieves FullFileInfo from the VFS.
	 *
	 * @throws FileNotFoundException
	 */
	private FullFileInfo getInfo(@NotNull final String path, @NotNull final Transaction txn) throws FileNotFoundException {
		if (Utils.isNull(path)) {
			throw new IllegalArgumentException("path cannot be null");
		}
		final ArrayByteIterable pathKey = StringBinding.stringToEntry(path);
		final ByteIterable iterable = infoStore.get(txn, pathKey);
		if (Utils.isNull(iterable)) {
			throw new FileNotFoundException("iterable was null and thus file info could not be created");
		}
		return new FullFileInfo(path, iterable);
	}

	/**
	 * Creates a new empty file for the path and sets the specified attributes.
	 *
	 * @throws IOException
	 */
	@Override
	public void createEmptyFile(@NotNull final String path, final long options, final FileAttribute attributes) throws IOException {
		final IOException error = env.computeInTransaction((@NotNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				createEmptyFile(path, options, attributes, txn);
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Utils.isNotNull(error)) {
			throw error;
		}
	}

	/**
	 * Creates a new empty file for the path and sets the specified attributes.
	 *
	 * @throws FileNotFoundException
	 */
	private void createEmptyFile(@NotNull final String path, final long options, final FileAttribute attributes, @NotNull final Transaction txn) throws FileNotFoundException {
		final File file = createFile(path, txn);
		final FullFileInfo info = getNewInfo(file, attributes);
		setInfo(path, info, txn);
	}

	/**
	 * Creates a new empty directory for the path and sets the specified attributes.
	 *
	 * @throws IOException
	 */
	@Override
	public void createEmptyDirectory(@NotNull final String path, final long options, final FileAttribute attributes) throws IOException {
		createEmptyFile(path, options, attributes);
	}

	/**
	 * Calls {@link jetbrains.exodus.vfs.VirtualFileSystem#createFile(Transaction, String)}
	 */
	private File createFile(@NotNull final String path, @NotNull final Transaction txn) {
		final String updatedPath;
		if (!path.startsWith(rootPath)) {
			updatedPath = rootPath + path;
		} else {
			updatedPath = path;
		}
		return vfs.createFile(txn, updatedPath);
	}

	/**
	 * Reads file from VFS and stores into data array.
	 */
	@Override
	public FileData read(@NotNull final String path, final int offset, final int readLength) throws IOException {
		if (readLength < 1) {
			throw new IOException("readLength cannot be empty");
		}

		if (Utils.isNull(path)) {
			throw new FileNotFoundException("path was null");
		}

		final FileData fileData = env.computeInTransaction((@NotNull final Transaction txn) -> {
			FileData toReturn = null;

			final File file = getExistingFile(path, txn);
			final long fileSize = vfs.getFileLength(txn, file);

			if (fileSize > 0) {
				if (Utils.isNotNull(file)) {
					final VfsInputStream inputStream = vfs.readFile(txn, file);
					try {
						final byte[] data = new byte[readLength];
						final int numRead = inputStream.read(data, offset, Math.min(readLength, data.length - offset));
						toReturn = new FileData(data, numRead);
					} catch (final IOException e) {
						LOGGER.warn("Read fault on path {}", path, e);
					}
				}
			} else {
				// initialize empty so IOException is not thrown
				toReturn = new FileData(new byte[0], 0);
			}
			return toReturn;
		});

		if (Utils.isNull(fileData)) {
			throw new IOException("Error reading file");
		}
		return fileData;
	}

	/**
	 * Writes a file or directory to VFS.
	 *
	 * @param filePath
	 * @param offset
	 * @param data
	 * @param int
	 * @param info
	 */
	private int writeAll(@NotNull final File file, final byte[] data, @NotNull final Transaction txn) throws IOException {
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
	public int write(@NotNull final String path, final int offset, final byte[] data, final int writeLength) throws IOException {
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

	private int write(@NotNull final File file, final int offset, final byte[] data, final int writeLength, final FullFileInfo info, @NotNull final Transaction txn)
	        throws IOException {
		try (DataOutputStream output = new DataOutputStream(vfs.writeFile(txn, file))) {
			output.write(data, offset, writeLength);
		}

		// This has to go outside the try statement so the buffer is fully written/flushed
		final long fileSize = vfs.getFileLength(txn, file);
		LOGGER.debug("wrote file: {}", file.getPath());

		FullFileInfo newInfo = info;
		if (Utils.isNull(info)) {
			// Figure out how to properly set attribute
			newInfo = getNewInfo(file, FileAttribute.NORMAL);
			newInfo.setCreationTime(file.getCreated());
			newInfo.setSize(fileSize);
		}
		setInfo(file.getPath(), newInfo, txn);
		return writeLength;
	}

	/**
	 * Stores FullFileInfo into VFS.
	 *
	 * @param path
	 * @param info
	 */
	private void setInfo(@NotNull final String path, @NotNull final FullFileInfo info, @NotNull final Transaction txn) {
		final ArrayByteIterable pathKey = StringBinding.stringToEntry(path);
		LOGGER.debug("set info for {} to {}", path, info);
		infoStore.put(txn, pathKey, info.toByteIterable());
	}

	/**
	 * Stores FullFileInfo into VFS.
	 *
	 * @param path
	 * @param info
	 */
	private void setInfo(@NotNull final String path, @NotNull final FullFileInfo info) {
		env.executeInTransaction((@NotNull final Transaction txn) -> {
			setInfo(path, info, txn);
		});
	}

	@Override
	/**
	 * Sets attributes on path.
	 */
	public void setAttributes(@NotNull final String path, final FileAttribute attributes) throws IOException {
		int toSet = FILE_ATTRIBUTE_NORMAL;
		// Will be null if coming from findExisting method
		if (Utils.isNotNull(attributes)) {
			toSet = attributes.val;
		}

		final FullFileInfo info = getInfo(path);
		info.dwFileAttributes = toSet;
		setInfo(path, info);
	}

	@Override
	public void setTime(@NotNull final String path, @NotNull final FILETIME creation, @NotNull final FILETIME lastAccess, @NotNull final FILETIME lastModification)
	        throws IOException {
		final FullFileInfo info = getInfo(path);
		info.ftCreationTime = creation;
		info.ftLastAccessTime = lastAccess;
		info.ftLastWriteTime = lastModification;
		setInfo(path, info);
	}

	@Override
	public void setSecurity(@NotNull final String path, final int kind, final byte[] data) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getSecurity(@NotNull final String path, final int kind, final byte[] out) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unlock(@NotNull final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Unlocking not implemented.");
	}

	@Override
	public void lock(@NotNull final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Locking not implemented.");
	}

	@Override
	public long truncate(@NotNull final String path) throws IOException {
		// final Node parent = item.getParent();

		/*
		 * vfs. final FullFileInfo fileInfo = handle.getFileInfo(); final FullFileInfo parentFileInfo = createHandle(parent.getPath(), parent).getFileInfo(); final FILETIME now =
		 * new FILETIME(new Date()); fileInfo.ftLastAccessTime = now; parentFileInfo.ftLastAccessTime = now; fileInfo.ftLastWriteTime = now; parentFileInfo.ftLastWriteTime = now;
		 * return ErrorCodes.ERROR_ALREADY_EXISTS.val;
		 */
		// TODO: complete
		return 0;
	}

	@Override
	public void setAllocationSize(@NotNull final String path, final int length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEndOfFile(@NotNull final String path, final int offset) {
		// TODO Auto-generated method stub
	}

	@Override
	public void flushFileBuffers(@NotNull final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup(@NotNull final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void close(@NotNull final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isDebug() {
		return true;
	}

	@Override
	public boolean isDebugStderrOutput() {
		return true;
	}

	/**
	 * // Dates will be automatically set to now by FileInfoBuilder. Number of links will default to 1.
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	private void createSampleItems() throws IOException {
		final IOException error = env.computeInTransaction((@NotNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				// Root - must be created
				File file = createFile(rootPath, txn);
				final FullFileInfo info = new FullFileInfo(rootPath, file.getDescriptor(), FileAttribute.DEVICE, volumeInfo.getVolumeSerialNumber());
				info.setSize(freeSpace.getTotalBytes());
				setInfo(rootPath, info, txn);

				// File 1 - empty
				createEmptyFile(rootPath + "1.txt", 0, FileAttribute.NORMAL, txn);

				// File 2 - 5 bytes
				file = createFile(rootPath + "2.txt", txn);
				writeAll(file, new byte[] { 'H', 'E', 'L', 'L', 'O' }, txn);

				// Directory - empty
				createEmptyFile(rootPath + "testFolder", 0, FileAttribute.DIRECTORY, txn);

				// File 3 - several bytes
				file = createFile("testFolder/3.TXT", txn);
				writeAll(file, "This file is within testFolder".getBytes(StandardCharsets.UTF_8), txn);
				LOGGER.debug("done creating samples");
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
