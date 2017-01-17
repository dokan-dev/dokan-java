package com.dokany.java.examples.mirrorfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyFileSystem;
import com.dokany.java.DokanyUtils;
import com.dokany.java.Win32FindStreamData;
import com.dokany.java.constants.FileAttribute;
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

public class MirrorFS extends DokanyFileSystem {
	private final static Logger LOGGER = LoggerFactory.getLogger(MirrorFS.class);

	/**
	 * Path matcher glob
	 */
	private final static String GLOB = "glob:";
	private final static java.nio.file.FileSystem DEFAULT_FS = FileSystems.getDefault();

	public MirrorFS(
	        @NotNull final DeviceOptions deviceOptions,
	        @NotNull final VolumeInformation volumeInfo,
	        @NotNull final FreeSpace freeSpace,
	        @NotNull final Date rootCreationDate,
	        @NotNull final String rootPath) throws FileNotFoundException {
		super(deviceOptions, volumeInfo, freeSpace, rootCreationDate, rootPath);

		if (!DokanyUtils.getPath(rootPath).toFile().exists()) {
			throw new FileNotFoundException("Cannot find directory: " + rootPath);
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
	public boolean doesPathExist(final String path) throws IOException {
		return Paths.get(path).toFile().exists();
	}

	@Override
	public Set<WIN32_FIND_DATA> findFilesWithPattern(final String path, final String pattern) throws IOException {
		final Set<WIN32_FIND_DATA> files = new HashSet<>();
		final String patternToMatch = Objects.isNull(pattern) ? "*" : pattern;
		final PathMatcher pathMatcher = DEFAULT_FS.getPathMatcher(GLOB + root + patternToMatch);

		// cannot use Files.find because of https://bugs.openjdk.java.net/browse/JDK-8039910
		// try (final Stream<Path> paths = Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> true)) {}

		/*-
		final FindFilesVisitor visitor = new FindFilesVisitor(pattern);
		Files.walkFileTree(rootPath, visitor);

		final Set<WIN32_FIND_DATA> files = visitor.getFiles();
		LOGGER.trace("foundFiles: {}", files);
		*/
		String startingPath = Paths.get(path).toString();
		LOGGER.trace("startingPath 1: {}", startingPath);

		LOGGER.trace("root: {}", root);

		if (startingPath.equals(File.separator)) {
			LOGGER.trace("inside and root is: {}", root);
			startingPath = root;
		}

		LOGGER.trace("startingPath: {}", startingPath);
		LOGGER.trace("startingPath as path: {}", Paths.get(startingPath));

		try (final Stream<Path> items = Files.list(Paths.get(startingPath))) {
			items.forEach(item -> {
				final WIN32_FIND_DATA findData = getFindData(item, pathMatcher);
				if (Objects.nonNull(findData)) {
					files.add(findData);
				}
			});
		}
		return files;
	}

	private final WIN32_FIND_DATA getFindData(final Path path, final PathMatcher pathMatcher) {
		WIN32_FIND_DATA findData = null;
		try {
			LOGGER.trace("getFindData for path: {}", path);
			if (pathMatcher.matches(path)) {
				LOGGER.trace("Found match: {}", path);
				final FullFileInfo info = getInfo(path);
				if (Objects.nonNull(info)) {
					findData = info.toWin32FindData();
				}
			}
		} catch (final IOException | Win32Exception e) {
			LOGGER.warn("Could not retrieve file info for {}", path, e);
		}
		return findData;
	}

	/**
	 * @param path
	 */
	@Override
	public FullFileInfo getInfo(final String path) throws IOException {
		return getInfo(DokanyUtils.getPath(path));
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private FullFileInfo getInfo(final Path path) throws IOException, Win32Exception {
		LOGGER.trace("relatvie in getinfo: {}", path.toString());

		final int attributesAsInt = Kernel32Util.getFileAttributes(DokanyUtils.normalize(path));

		final EnumIntegerSet<FileAttribute> attributes = FileAttribute.fromInt(attributesAsInt);

		/*-
		if (path.toString().equals("\\")) {
			attribute = FileAttribute.DEVICE;
		} else if (path.toString().equals("\\$Recycle.Bin")) {
			attribute = FileAttribute.READONLY;
		}
		*/

		final BasicFileAttributes basicAttributes = DokanyUtils.getBasicAttributes(path).readAttributes();
		long fileIndex = 0;
		final Object fileKey = basicAttributes.fileKey();
		if (Objects.nonNull(fileKey)) {
			fileIndex = (long) fileKey;
		}

		LOGGER.trace("rootPath: {}", root);
		LOGGER.trace("File.separator: {}", File.separator);

		String pathNameForInfo = path.toString();
		LOGGER.trace("pathNameForInfo: {}", pathNameForInfo);
		LOGGER.trace("pathNameForInfo.equals(File.separator): {}", pathNameForInfo.equals(File.separator));

		if (!pathNameForInfo.equals(File.separator)) {
			pathNameForInfo = Paths.get(root).relativize(path).toString();
			LOGGER.trace("relativizing: {}", pathNameForInfo);
		}

		return new FullFileInfo(
		        pathNameForInfo,
		        fileIndex,
		        attributes,
		        volumeInfo.getVolumeSerialNumber(),
		        DokanyUtils.toFILETIME(basicAttributes.creationTime()),
		        DokanyUtils.toFILETIME(basicAttributes.lastAccessTime()),
		        DokanyUtils.toFILETIME(basicAttributes.lastModifiedTime()));
	}

	@Override
	public void move(final String oldPath, final String newPath, final boolean replaceIfExisting) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Check if file can be deleted. Actual deletion occurs in {@link #cleanup(String, DokanyFileInfo)}.
	 *
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void deleteFile(final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		final File file = DokanyUtils.getPath(path).toFile();

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
	public void deleteDirectory(final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		final File directory = DokanyUtils.getPath(path).toFile();

		if (directory.isFile()) {
			throw new AccessDeniedException("Path is a file: " + directory);
		}

		DokanyUtils.setDeleteStatus(directory, dokanyFileInfo);
	}

	@Override
	public FileData read(final String path, final int offset, final int readLength) throws IOException {

		// if (info.Context == null) // memory mapped read {

		LOGGER.trace("read: {}", path);
		// final File file = Paths.get(path).toFile();
		final File file = new File(path);
		LOGGER.trace("file: {}", file);

		int numRead = 0;
		final byte[] data = new byte[readLength];

		try (FileInputStream fis = new FileInputStream(file)) {
			numRead = fis.read(data, offset, readLength);
		}

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
	public int write(final String path, final int offset, final byte[] data, final int writeLength) throws IOException {

		final File file = Paths.get(path).toFile();

		try (FileOutputStream fis = new FileOutputStream(file)) {
			fis.write(data, offset, writeLength);
		}

		return writeLength - offset;
	}

	/**
	 * @param path
	 * @param options
	 * @param attributes
	 */
	@Override
	public void createEmptyFile(final String path, final long options, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @param path
	 * @param options
	 * @param attributes
	 */
	// @Override
	@Override
	public void createEmptyDirectory(final String path, final long options, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @param path
	 */
	@Override
	public void flushFileBuffers(final String path) throws IOException {
		// ((FileStream) (info.Context)).Flush();

	}

	/**
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void cleanup(final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		// File already closed by DokanyOperationsProxy

		if (dokanyFileInfo.deleteOnClose()) {
			// does not matter file or directory
			Files.delete(DokanyUtils.getPath(path));
		}
	}

	// TODO: May not be necessary as this can be completely done in DokanyOperationsProxy
	/**
	 * @param path
	 * @param dokanyFileInfo
	 */
	@Override
	public void close(final String path, final DokanyFileInfo dokanyFileInfo) throws IOException {
		dokanyFileInfo._context = 0;
		// (dokanyFileInfo._context as FileStream)?.Dispose();

	}

	/**
	 * @param path
	 * @param kind
	 * @param out
	 */
	@Override
	public int getSecurity(final String path, final int kind, final byte[] out) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param path
	 * @param kind
	 * @param out
	 */
	@Override
	public void setSecurity(final String path, final int kind, final byte[] data) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public long truncate(final String path) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAllocationSize(final String path, final int length) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEndOfFile(final String path, final int offset) throws IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @param path
	 * @param attributes
	 */
	@Override
	public void setAttributes(final String path, final EnumIntegerSet<FileAttribute> attributes) throws IOException {
		Kernel32.INSTANCE.SetFileAttributes(path, new DWORD(attributes.toInt()));
	}

	@Override
	public void unlock(final String path, final int offset, final int length) throws IOException {
		throw new UnsupportedOperationException("unlock: Not yet implemented");
	}

	@Override
	public void lock(final String path, final int offset, final int length) throws IOException {
		throw new UnsupportedOperationException("lock: Not yet implemented");
	}

	@Override
	public Set<Win32FindStreamData> findStreams(final String path) throws IOException {
		throw new UnsupportedOperationException("findStreams: Not yet implemented");
	}

	/**
	 * @param path
	 * @param creation
	 * @param lastAccess
	 * @param lastModification
	 */
	@Override
	public void setTime(final String path, final FILETIME creation, final FILETIME lastAccess, final FILETIME lastModification) throws IOException {
		final BasicFileAttributeView attributes = DokanyUtils.getBasicAttributes(path);

		final FileTime create = DokanyUtils.toFileTime(creation);
		final FileTime access = DokanyUtils.toFileTime(lastAccess);
		final FileTime modified = DokanyUtils.toFileTime(lastModification);

		attributes.setTimes(modified, access, create);
	}

	private final class FindFilesVisitor extends SimpleFileVisitor<Path> {
		private final Set<WIN32_FIND_DATA> files = new HashSet<>();
		final PathMatcher pathMatcher;

		public FindFilesVisitor(final String pattern) {
			super();
			LOGGER.trace("rootPath: {}", root);
			LOGGER.trace("pattern: {}", pattern);

			final String patternToMatch = Objects.isNull(pattern) ? "*" : pattern;
			pathMatcher = DEFAULT_FS.getPathMatcher(GLOB + root + patternToMatch);
		}

		public final Set<WIN32_FIND_DATA> getFiles() {
			return files;
		}

		@Override
		public final FileVisitResult visitFile(final Path path,
		        final BasicFileAttributes basicFileAttributes) {
			files.add(getFindData(path, pathMatcher));
			return FileVisitResult.CONTINUE;
		}

		@Override
		public final FileVisitResult postVisitDirectory(final Path path, final IOException e) {
			getFindData(path, pathMatcher);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public final FileVisitResult visitFileFailed(final Path path, final IOException e) {
			LOGGER.warn("Failed to visit file: {}", path, e);
			return FileVisitResult.CONTINUE;
		}
	}
}