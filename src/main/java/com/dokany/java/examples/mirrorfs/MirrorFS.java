package com.dokany.java.examples.mirrorfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

import com.dokany.java.DokanyFileSystem;
import com.dokany.java.DokanyUtils;
import com.dokany.java.Win32FindStreamData;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.FileSystemFeature;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.FullFileInfo;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.platform.win32.WinDef.DWORD;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class MirrorFS extends DokanyFileSystem {

	IOCase ioCase;

	public MirrorFS(
	        @NonNull final DeviceOptions deviceOptions,
	        @NonNull final VolumeInformation volumeInfo,
	        @NonNull final FreeSpace freeSpace,
	        @NonNull final Date rootCreationDate,
	        @NonNull final String rootPath) throws FileNotFoundException {
		super(deviceOptions, volumeInfo, freeSpace, rootCreationDate, rootPath);

		if (!DokanyUtils.getPath(rootPath).toFile().exists()) {
			throw new FileNotFoundException("Cannot find directory: " + rootPath);
		}

		if (volumeInfo.getFileSystemFeatures().contains(FileSystemFeature.CASE_PRESERVED_NAMES)
		        && volumeInfo.getFileSystemFeatures().contains(FileSystemFeature.CASE_PRESERVED_NAMES)) {
			ioCase = IOCase.SENSITIVE;
		} else {
			ioCase = IOCase.INSENSITIVE;
		}
	}

	@Override
	public void mounted() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void unmounted() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean doesPathExist(@NonNull final String path) throws IOException {
		return Paths.get(path).toFile().exists();
	}

	@Override
	public Set<WIN32_FIND_DATA> findFilesWithPattern(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo, final String pattern) throws IOException {
		val normalizedPath = DokanyUtils.normalize(path);
		val normalizedPattern = DokanyUtils.normalize(pattern);
		log.trace("findFilesWithPattern for {} with pattern {}", normalizedPath, normalizedPattern);
		log.trace("dokanyFileInfo in findFilesWithPattern: {}", dokanyFileInfo);

		val files = new HashSet<WIN32_FIND_DATA>();

		val startingPath = getFullPath(normalizedPath);

		val patternToMatch = DokanyUtils.normalize(Objects.isNull(pattern) ? startingPath + "*" : startingPath + normalizedPattern);

		@Cleanup
		val items = Files.list(Paths.get(startingPath));
		items.forEach(itemPath -> {
			addFindData(itemPath, patternToMatch, files);
		});
		return files;
	}

	private final void addFindData(@NonNull final Path path, @NonNull final String pattern, @NonNull final Set<WIN32_FIND_DATA> findData) {
		val normalizedPath = DokanyUtils.normalize(path);
		log.trace("getFindData for path {} with pattern {}", normalizedPath, pattern);

		// root already normalized and has trailing slash
		final boolean isMatch = FilenameUtils.wildcardMatch(normalizedPath, pattern, ioCase);

		if (isMatch) {
			try {
				log.trace("Found match: {}", normalizedPath);
				val info = getInfo(normalizedPath, pattern.replace("*", ""));
				if (Objects.nonNull(info)) {
					findData.add(info.toWin32FindData());
				}
			} catch (final IOException | Win32Exception e) {
				log.warn("Could not retrieve file info for {}", normalizedPath, e);
			}
		}
	}

	/**
	 *
	 * @param normalizedPath with or without root. This must already be normalized using {@link com.dokany.java.DokanyUtils#normalize(path))}.
	 * @return path full path to from mirror (this means it will include root)
	 */
	private String getFullPath(@NonNull final String normalizedPath) {
		String fullPath;
		if (FilenameUtils.equals(normalizedPath, DokanyUtils.UNIX_SEPARATOR)) {
			fullPath = root;
		} else if (normalizedPath.startsWith(DokanyUtils.UNIX_SEPARATOR)) {
			fullPath = root + normalizedPath;
		} else {
			fullPath = normalizedPath;
		}
		// replace any double slashes
		fullPath = fullPath.replace(DokanyUtils.UNIX_SEPARATOR + DokanyUtils.UNIX_SEPARATOR, DokanyUtils.UNIX_SEPARATOR);
		return fullPath;
	}

	/**
	 * @param path path which may or may not be normalized
	 * @return FullFileInfo
	 */
	@Override
	public FullFileInfo getInfo(@NonNull final String path) throws IOException {
		return getInfo(DokanyUtils.normalize(path), null);
	}

	/**
	 *
	 * @param path
	 * @param pathPartToRemove this may be null
	 * @return FullFileInfo
	 * @throws IOException
	 */
	private FullFileInfo getInfo(@NonNull final String normalizedPath, final String pathPartToRemove) throws IOException, Win32Exception {
		// path will already be normalized
		log.trace("getInfo for {} with pathPartToRemove {}", normalizedPath, pathPartToRemove);

		val fullPath = getFullPath(normalizedPath);

		val attributesAsInt = Kernel32Util.getFileAttributes(fullPath);

		val attributes = FileAttribute.fromInt(attributesAsInt);

		/*-
		if (path.toString().equals("\\")) {
			attribute = FileAttribute.DEVICE;
		} else if (path.toString().equals("\\$Recycle.Bin")) {
			attribute = FileAttribute.READONLY;
		}
		*/

		val basicAttributes = DokanyUtils.getBasicAttributes(fullPath).readAttributes();
		long fileIndex = 0;
		val fileKey = basicAttributes.fileKey();
		if (Objects.nonNull(fileKey)) {
			fileIndex = (long) fileKey;
		}

		log.trace("rootPath: {}", root);

		val pathWithoutRoot = DokanyUtils.trimTailSeparator(Objects.nonNull(pathPartToRemove) ? normalizedPath.replace(pathPartToRemove, "") : normalizedPath);
		log.trace("pathWithoutRoot: {}", pathWithoutRoot);

		return new FullFileInfo(
		        pathWithoutRoot,
		        fileIndex,
		        attributes,
		        volumeInfo.getSerialNumber(),
		        DokanyUtils.toFILETIME(basicAttributes.creationTime()),
		        DokanyUtils.toFILETIME(basicAttributes.lastAccessTime()),
		        DokanyUtils.toFILETIME(basicAttributes.lastModifiedTime()));
	}

	@Override
	public void move(@NonNull final String oldPath, @NonNull final String newPath, final boolean replaceIfExisting) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Check if file can be deleted. Actual deletion occurs in {@link #cleanup(String, DokanyFileInfo)}.
	 *
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void deleteFile(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException {
		val file = DokanyUtils.getPath(path).toFile();

		if (file.isDirectory()) {
			throw new AccessDeniedException("Path is a directory: " + file);
		}

		if (!file.exists()) {
			throw new FileNotFoundException("Path does not exist: " + file);
		}

		DokanyUtils.setDeleteStatus(file, dokanyFileInfo);
	}

	/**
	 * Check if directory can be deleted. Actual deletion occurs in {@link #cleanup(String, DokanyFileInfo)}.
	 *
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void deleteDirectory(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException {
		val directory = DokanyUtils.getPath(path).toFile();

		if (directory.isFile()) {
			throw new AccessDeniedException("Path is a file: " + directory);
		}

		DokanyUtils.setDeleteStatus(directory, dokanyFileInfo);
	}

	@Override
	public FileData read(@NonNull final String path, final int offset, final int readLength) throws IOException {
		val normalizedPath = DokanyUtils.normalize(path);
		val fullPath = getFullPath(normalizedPath);
		new File("C:/development");
		// if (info.Context == null) // memory mapped read {

		log.trace("read: {}", fullPath);

		int numRead = 0;
		val data = new byte[readLength];

		@Cleanup
		final FileInputStream fis = new FileInputStream(fullPath);
		numRead = fis.read(data, offset, readLength);

		return new FileData(data, numRead);
		/*
		 * else // normal read { var stream = info.Context as FileStream; lock (stream) //Protect from overlapped read { stream.Position = offset; bytesRead = stream.Read(buffer,
		 * 0, buffer.Length); } }
		 */
	}

	/**
	 *
	 * @param path
	 * @param offset
	 * @param data
	 * @param writeLength
	 */
	@Override
	public int write(@NonNull final String path, final int offset, @NonNull final byte[] data, final int writeLength) throws IOException {
		val normalizedPath = DokanyUtils.normalize(path);
		val fullPath = getFullPath(normalizedPath);

		@Cleanup
		final FileOutputStream fis = new FileOutputStream(fullPath);
		fis.write(data, offset, writeLength);

		return writeLength - offset;
	}

	/**
	 * @param path
	 * @param options
	 * @param attributes
	 */
	@Override
	public void createEmptyFile(@NonNull final String path, final long options, @NonNull final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @param path
	 * @param options
	 * @param attributes
	 */
	// @Override
	@Override
	public void createEmptyDirectory(@NonNull final String path, final long options, @NonNull final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @param path
	 */
	@Override
	public void flushFileBuffers(@NonNull final String path) throws IOException {
		// ((FileStream) (info.Context)).Flush();

	}

	/**
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void cleanup(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException {
		// File already closed by DokanyOperationsProxy
		if (dokanyFileInfo.deleteOnClose()) {
			// does not matter file or directory
			Files.delete(DokanyUtils.getPath(path));
		}
		log.trace("dokanyFileInfo in cleanup for path {} {} ", path, dokanyFileInfo);
	}

	// TODO: May not be necessary as this can be completely done in DokanyOperationsProxy
	/**
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void close(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException {
		dokanyFileInfo.Context = 0;
		// (dokanyFileInfo.Context as FileStream)?.Dispose();
		log.trace("dokanyFileInfo in close for path {} {} ", path, dokanyFileInfo);

	}

	/**
	 * @param path
	 * @param kind
	 * @param out
	 */
	@Override
	public int getSecurity(@NonNull final String path, final int kind, final byte[] out) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param path
	 * @param kind
	 * @param out
	 */
	@Override
	public void setSecurity(@NonNull final String path, final int kind, final byte[] data) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public long truncate(@NonNull final String path) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAllocationSize(@NonNull final String path, final int length) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEndOfFile(@NonNull final String path, final int offset) throws IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @param path
	 * @param attributes
	 */
	@Override
	public void setAttributes(@NonNull final String path, @NonNull final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		Kernel32.INSTANCE.SetFileAttributes(path, new DWORD(attributes.toInt()));
	}

	@Override
	public void unlock(@NonNull final String path, final int offset, final int length) throws IOException {
		throw new UnsupportedOperationException("unlock: Not yet implemented");
	}

	@Override
	public void lock(@NonNull final String path, final int offset, final int length) throws IOException {
		throw new UnsupportedOperationException("lock: Not yet implemented");
	}

	@Override
	public Set<Win32FindStreamData> findStreams(@NonNull final String path) throws IOException {
		throw new UnsupportedOperationException("findStreams: Not yet implemented");
	}

	/**
	 * @param path
	 * @param creation
	 * @param lastAccess
	 * @param lastModification
	 */
	@Override
	public void setTime(@NonNull final String path, @NonNull final FILETIME creation, @NonNull final FILETIME lastAccess, @NonNull final FILETIME lastModification)
	        throws IOException {
		val attributes = DokanyUtils.getBasicAttributes(path);

		val create = DokanyUtils.toFileTime(creation);
		val access = DokanyUtils.toFileTime(lastAccess);
		val modified = DokanyUtils.toFileTime(lastModification);

		attributes.setTimes(modified, access, create);
	}
}