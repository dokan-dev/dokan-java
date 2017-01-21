package com.dokany.java.examples.memoryfs;

import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NORMAL;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.dokany.java.DokanyFileSystem;
import com.dokany.java.Win32FindStreamData;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.FullFileInfo;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

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
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class MemoryFS extends DokanyFileSystem {

	/**
	 * Path matcher glob
	 */
	static String GLOB = "glob:";
	static java.nio.file.FileSystem DEFAULT_FS = FileSystems.getDefault();

	// VFS - virtual file store provided by xodus
	Environment env;
	VirtualFileSystem vfs;
	Store infoStore;

	public MemoryFS(
	        @NonNull final DeviceOptions deviceOptions,
	        @NonNull final VolumeInformation volumeInfo,
	        @NonNull final FreeSpace freeSpace,
	        @NonNull final Date rootCreationDate,
	        @NonNull final String rootPath) throws IOException {
		super(deviceOptions, volumeInfo, freeSpace, rootCreationDate, rootPath);

		// Try to create store location in temp directory
		val fileStorePath = Files.createTempDirectory("dokany-java_");

		// Init VFS
		env = Environments.newInstance(fileStorePath.toString());
		vfs = new VirtualFileSystem(env);

		// File store name
		val fileStoreName = "com.dokany.java.fileinfo";

		// Create/open store and save in fileInfoStore
		// must not be read only so that store can be created
		infoStore = env.computeInTransaction((@NonNull final Transaction txn) -> env.openStore(fileStoreName, StoreConfig.WITHOUT_DUPLICATES, txn));

		createSampleItems();
	}

	@Override
	public void mounted() {
		// try {
		// } catch (final IOException e) {
		// throw new DokanyException(MountError.DOKAN_START_ERROR.val, e);
		// }
	}

	@Override
	public void unmounted() {
		/*
		 * vfs.shutdown(); if (env.isOpen()) { env.close(); }
		 */
	}

	/**
	 * Determines if file exists already or not. Calls {@link #getExistingFile(String)}.
	 *
	 * @param path
	 */
	@Override
	public boolean doesPathExist(@NonNull final String path) {
		return Objects.nonNull(getExistingFile(path));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	public File getExistingFile(@NonNull final String path) {
		log.trace("getExistingFile: {}", path);
		// will be null if openFile does not find it
		return env.computeInReadonlyTransaction((@NonNull final Transaction txn) -> getExistingFile(path, txn));
	}

	/**
	 * Retrieves existing path
	 *
	 * @param path
	 * @return file or null if path does not exist
	 */
	private File getExistingFile(@NonNull final String path, @NonNull final Transaction txn) {
		return vfs.openFile(txn, path, false);
	}

	/**
	 * Throws an exception if file cannot be found for specified path.
	 *
	 * @param path
	 * @return file or FileNotFoundException
	 * @throws FileNotFoundException
	 */
	private File getExistingFileWithException(@NonNull final String path, @NonNull final Transaction txn) throws FileNotFoundException {
		val file = getExistingFile(path, txn);
		if (Objects.isNull(file)) {
			throw new FileNotFoundException("Could not find file for path: " + path);
		}
		return file;
	}

	/*-
	@Override
	public Set<WIN32_FIND_DATA> findFiles(@NonNull final String pathToSearch) {
		final Set<WIN32_FIND_DATA> files = new HashSet<>();

		env.executeInReadonlyTransaction((@NonNull final Transaction txn) -> {
			vfs.getFiles(txn).forEach(file -> {
				final String currentPath = file.getPath();

				// Do not match pathToSearch or it will get listed in the directory
				if (!pathToSearch.equals(pathToSearch) && currentPath.startsWith(currentPath)) {
					try {
						files.add(getInfo(currentPath, txn).toWin32FindData());
					} catch (final FileNotFoundException e) {
						log.warn("Failed to add found file because of caught exception", e);
					}
				}
			});
		});
		return files;
	}
	*/

	@Override
	public Set<WIN32_FIND_DATA> findFilesWithPattern(@NonNull final String pathToSearch, @NonNull final String pattern) {
		val files = new HashSet<WIN32_FIND_DATA>();

		log.debug("findFilesWithPattern memoryfs:   path  {};     pattern {}", pathToSearch, pattern);
		// Only use if pattern is not null
		val pathMatcher = DEFAULT_FS.getPathMatcher(GLOB + root + pattern);

		env.executeInReadonlyTransaction((@NonNull final Transaction txn) -> {
			vfs.getFiles(txn).forEach(file -> {
				val currentPath = file.getPath();
				log.trace("getFiles path: {}", currentPath);

				// Do not match pathToSearch or it will get listed in the directory
				if (!currentPath.equals(pathToSearch) && pathMatcher.matches(Paths.get(currentPath))) {
					try {
						files.add(getInfo(currentPath, txn).toWin32FindData());
						log.trace("Added {}", currentPath);
					} catch (final FileNotFoundException e) {
						log.warn("Failed to add found file because of caught exception", e);
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
	public void deleteFile(@NonNull final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		delete(path);
	}

	@Override
	public void deleteDirectory(@NonNull final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		delete(path);
	}

	private void delete(@NonNull final String path) {
		env.executeInTransaction((@NonNull final Transaction txn) -> {
			vfs.deleteFile(txn, path);
		});
	}

	/**
	 *
	 */
	@Override
	public void move(@NonNull final String oldPath, @NonNull final String newPath, final boolean replaceIfExisting) throws IOException {
		throw new UnsupportedOperationException("Not yet implemented");

		/*
		 * env.executeInTransaction((@NonNull final Transaction txn) -> { final VfsInputStream inputStream = vfs.readFile(txn, descriptor); inputStream.read(data, offset,
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
	private FullFileInfo getNewInfo(@NonNull final File file, final EnumIntegerSet<FileAttribute> attributes) throws FileNotFoundException {
		return new FullFileInfo(file.getPath(), file.getDescriptor(), attributes, getVolumeInfo().getSerialNumber());
	}

	/**
	 * This method retrieves FullFileInfo from the VFS.
	 *
	 * @throws IOException
	 */
	@Override
	public FullFileInfo getInfo(@NonNull final String path) throws IOException {
		log.debug("getInfo for {}", path);

		val result = env.computeInReadonlyTransaction((@NonNull final Transaction txn) -> {
			FullFileInfo toReturn = null;
			try {
				toReturn = getInfo(path, txn);
			} catch (final IOException e) {
				log.warn("Could not retrieve info", e);
			}
			return toReturn;
		});

		if (Objects.isNull(result)) {
			throw new IOException();
		}
		return result;
	}

	/**
	 * This method retrieves FullFileInfo from the VFS.
	 *
	 * @throws FileNotFoundException
	 */
	private FullFileInfo getInfo(@NonNull final String path, @NonNull final Transaction txn) throws FileNotFoundException {
		if (Objects.isNull(path)) {
			throw new IllegalArgumentException("path cannot be null");
		}
		val pathKey = StringBinding.stringToEntry(path);
		val iterable = infoStore.get(txn, pathKey);
		if (Objects.isNull(iterable)) {
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
	public void createEmptyFile(@NonNull final String path, final long options, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		final IOException error = env.computeInTransaction((@NonNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				createEmptyFile(path, options, attributes, txn);
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Objects.nonNull(error)) {
			throw error;
		}
	}

	/**
	 * Creates a new empty file for the path and sets the specified attributes.
	 *
	 * @throws FileNotFoundException
	 */
	private void createEmptyFile(@NonNull final String path, final long options, final EnumIntegerSet<FileAttribute> attributes, @NonNull final Transaction txn)
	        throws FileNotFoundException {
		val file = createFile(path, txn);
		val info = getNewInfo(file, attributes);
		setInfo(path, info, txn);
	}

	/**
	 * Creates a new empty directory for the path and sets the specified attributes.
	 *
	 * @throws IOException
	 */
	@Override
	public void createEmptyDirectory(@NonNull final String path, final long options, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		createEmptyFile(path, options, attributes);
	}

	/**
	 * Calls {@link jetbrains.exodus.vfs.VirtualFileSystem#createFile(Transaction, String)}
	 */
	private File createFile(@NonNull final String path, @NonNull final Transaction txn) {
		final String updatedPath;
		if (!path.startsWith(root)) {
			updatedPath = root + path;
		} else {
			updatedPath = path;
		}
		return vfs.createFile(txn, updatedPath);
	}

	/**
	 * Reads file from VFS and stores into data array.
	 */
	@Override
	public FileData read(@NonNull final String path, final int offset, final int readLength) throws IOException {
		if (readLength < 1) {
			throw new IOException("readLength cannot be empty");
		}

		if (Objects.isNull(path)) {
			throw new FileNotFoundException("path cannot be null");
		}

		val fileData = env.computeInTransaction((@NonNull final Transaction txn) -> {
			FileData toReturn = null;

			val file = getExistingFile(path, txn);
			val fileSize = vfs.getFileLength(txn, file);

			if (fileSize > 0) {
				if (Objects.nonNull(file)) {
					final VfsInputStream inputStream = vfs.readFile(txn, file);
					try {
						val data = new byte[readLength];
						val numRead = inputStream.read(data, offset, Math.min(readLength, data.length - offset));
						toReturn = new FileData(data, numRead);
					} catch (final IOException e) {
						log.warn("Read fault on path {}", path, e);
					}
				}
			} else {
				// initialize empty so IOException is not thrown
				log.debug("fileSize was 0; sending back empty FileData");
				toReturn = new FileData(new byte[0], 0);
			}
			return toReturn;
		});

		if (Objects.isNull(fileData)) {
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
	private int writeAll(@NonNull final File file, final byte[] data, @NonNull final Transaction txn) throws IOException {
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
	public int write(@NonNull final String path, final int offset, final byte[] data, final int writeLength) throws IOException {
		if (Objects.isNull(path)) {
			throw new FileNotFoundException("Path was null");
		}

		val error = env.computeInTransaction((@NonNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				val file = getExistingFileWithException(path, txn);
				write(file, offset, data, writeLength, null, txn);
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Objects.nonNull(error)) {
			throw error;
		}

		return writeLength;
	}

	private int write(@NonNull final File file, final int offset, final byte[] data, final int writeLength, final FullFileInfo info, @NonNull final Transaction txn)
	        throws IOException {
		@Cleanup
		final DataOutputStream output = new DataOutputStream(vfs.writeFile(txn, file));
		output.write(data, offset, writeLength);

		// This has to go outside the try statement so the buffer is fully written/flushed
		final long fileSize = vfs.getFileLength(txn, file);
		log.debug("wrote file: {}", file.getPath());

		FullFileInfo newInfo = info;
		if (Objects.isNull(info)) {
			// Figure out how to properly set attribute
			val attributes = new EnumIntegerSet<>(FileAttribute.class);
			attributes.add(FileAttribute.NORMAL);
			newInfo = getNewInfo(file, attributes);

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
	private void setInfo(@NonNull final String path, @NonNull final FullFileInfo info, @NonNull final Transaction txn) {
		val pathKey = StringBinding.stringToEntry(path);
		infoStore.put(txn, pathKey, info.toByteIterable());
		log.debug("Stored info for {}", path);
	}

	/**
	 * Stores FullFileInfo into VFS.
	 *
	 * @param path
	 * @param info
	 */
	private void setInfo(@NonNull final String path, @NonNull final FullFileInfo info) {
		env.executeInTransaction((@NonNull final Transaction txn) -> {
			setInfo(path, info, txn);
		});
	}

	@Override
	/**
	 * Sets attributes on path.
	 */
	public void setAttributes(@NonNull final String path, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		int attributeAsInt = FILE_ATTRIBUTE_NORMAL;
		// Will be null if coming from findExisting method
		if (Objects.nonNull(attributes)) {
			attributeAsInt = attributes.toInt();
		}

		val info = getInfo(path);
		info.dwFileAttributes = attributeAsInt;
		setInfo(path, info);
	}

	@Override
	public void setTime(@NonNull final String path, @NonNull final FILETIME creation, @NonNull final FILETIME lastAccess, @NonNull final FILETIME lastModification)
	        throws IOException {
		val info = getInfo(path);
		info.ftCreationTime = creation;
		info.ftLastAccessTime = lastAccess;
		info.ftLastWriteTime = lastModification;
		setInfo(path, info);
	}

	@Override
	public void setSecurity(@NonNull final String path, final int kind, final byte[] data) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getSecurity(@NonNull final String path, final int kind, final byte[] out) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unlock(@NonNull final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Unlocking not implemented.");
	}

	@Override
	public void lock(@NonNull final String path, final int offset, final int length) {
		throw new UnsupportedOperationException("Locking not implemented.");
	}

	@Override
	public long truncate(@NonNull final String path) throws IOException {
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
	public void setAllocationSize(@NonNull final String path, final int length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEndOfFile(@NonNull final String path, final int offset) {
		// TODO Auto-generated method stub
	}

	@Override
	public void flushFileBuffers(@NonNull final String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup(@NonNull final String path, final DokanyFileInfo dokanyFileInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void close(@NonNull final String path, final DokanyFileInfo dokanyFileInfo) {
		// TODO Auto-generated method stub
	}

	/**
	 * // Dates will be automatically set to now by FileInfoBuilder. Number of links will default to 1.
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	private void createSampleItems() throws IOException {
		val error = env.computeInTransaction((@NonNull final Transaction txn) -> {
			IOException toReturn = null;
			try {
				// Root - must be created
				File file = createFile(root, txn);
				val attributes = new EnumIntegerSet<>(FileAttribute.class);
				attributes.add(FileAttribute.DEVICE);
				val info = new FullFileInfo(root, file.getDescriptor(), attributes,
				        volumeInfo.getSerialNumber());
				info.setSize(freeSpace.getTotalBytes());
				setInfo(root, info, txn);

				// File 1 - empty
				attributes.clear();
				attributes.add(FileAttribute.NORMAL);
				createEmptyFile(root + "1.txt", 0, attributes, txn);

				// File 2 - 5 bytes
				file = createFile(root + "2.txt", txn);
				writeAll(file, new byte[] { 'H', 'E', 'L', 'L', 'O' }, txn);

				// Directory - empty
				attributes.clear();
				attributes.add(FileAttribute.DIRECTORY);
				createEmptyFile(root + "testFolder", 0, attributes, txn);

				// File 3 - several bytes
				file = createFile("testFolder/3.TXT", txn);
				writeAll(file, "This file is within testFolder".getBytes(StandardCharsets.UTF_8), txn);
				log.debug("done creating samples");
			} catch (final IOException e) {
				toReturn = e;
			}
			return toReturn;
		});

		if (Objects.nonNull(error)) {
			throw error;
		}
	}
}
