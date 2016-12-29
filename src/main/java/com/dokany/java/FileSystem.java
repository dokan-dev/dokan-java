package com.dokany.java;

import static com.dokany.java.constants.FileSystemFeatures.CasePreservedNames;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

/**
 * This should be extended by file system providers.
 *
 * @param <TNode>
 */
public interface FileSystem<TNode> {

	public default boolean isDefaultLog() {
		return isDebug();
	}

	public default boolean isDebug() {
		return false;
	}

	public default boolean isDebugStderrOutput() {
		return false;
	}

	public Path getRootPath();

	public default Date getRootCreateDate() {
		return new Date();
	}

	public default int getMaxComponentLength() {
		return 256;
	}

	public long getTotalBytesAvailable();

	public long getUsedBytes();

	public default long getFreeBytesAvailable() {
		return getTotalBytesAvailable() - getUsedBytes();
	}

	/**
	 * Default is 4096.
	 *
	 * @return
	 */
	public default int getAllocationUnitSize() {
		return 4096;
	}

	/**
	 * Default is 4096.
	 *
	 * @return
	 */
	public default int getSectorSize() {
		return 4096;
	}

	/**
	 * Default is 10000.
	 *
	 * @return
	 */
	public default int getTimeout() {
		return 10000;
	}

	/**
	 * Default is 0x00000000
	 *
	 * @return
	 */
	public default int getVolumeSerialNumber() {
		return 0x12345678;
	}

	/**
	 * Default is VOLUME;
	 *
	 * @return
	 */
	public default String getVolumeName() {
		return "VOLUME";
	}

	/**
	 * Default is DOKANY.
	 *
	 * @return
	 */
	public default String getFileSystemName() {
		return "DOKANY";
	}

	public default int getSecurity(final FileHandle<TNode> handle, final int kind, final byte[] out) throws IOException {
		return 0;
	}

	public void setSecurity(final FileHandle<TNode> handle, final int kind, final byte[] data) throws IOException;

	/**
	 * Default is FileSystemFeatures.CasePreservedNames
	 *
	 * @return
	 */
	public default int getFileSystemFeatures() {
		return CasePreservedNames.val;
	}

	public ByHandleFileInfo getInfo(final FileHandle<TNode> handle) throws IOException;

	public FileHandle<TNode> getHandle(final TNode node) throws IOException;

	public FileHandle<TNode> getHandle(final Path nodePath, final long id) throws IOException;

	public FileHandle<TNode> createHandle(final Path nodePath) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public TNode createFile(
	        final Path path,
	        long options,
	        final FileAttribute attributes) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public TNode createDirectory(
	        final Path path,
	        final long options,
	        final FileAttribute attributes)
	        throws IOException;

	public TNode findExisting(final Path nodePath, final boolean isDirectory) throws IOException;

	public void cleanup(final FileHandle<TNode> handle) throws IOException;

	public void close(final FileHandle<TNode> handle) throws IOException;

	public void mounted() throws IOException;

	public void unmounted() throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void unlock(final FileHandle<TNode> handle, final long byteOffset, final long length) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void lock(final FileHandle<TNode> handle, final long byteOffset, final long length) throws IOException;

	public void setAllocationSize(final FileHandle<TNode> handle, final long length) throws IOException;

	public void setEndOfFile(final FileHandle<TNode> handle, final long byteOffset) throws IOException;

	public void move(final FileHandle<TNode> oldHandle, final FileHandle<TNode> newHandle, final boolean replaceIfExisting) throws IOException;

	public void deleteFile(final FileHandle<TNode> handle) throws IOException;

	public void deleteDirectory(final FileHandle<TNode> handle) throws IOException;

	public int read(final FileHandle<TNode> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public int write(final FileHandle<TNode> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public long truncate(final FileHandle<TNode> handle) throws IOException;

	public void findFiles(final FileHandle<TNode> handle, final FileEmitter emitter) throws IOException;

	public void findStreams(final FileHandle<TNode> handle, final StreamEmitter emitter) throws IOException;

	public void flushFileBuffers(final FileHandle<TNode> handle) throws IOException;

	public void setAttributes(final FileHandle<TNode> handle, final FileAttribute attributes);

	public void setTime(final FileHandle<TNode> handle, final Date creation, final Date lastAccess, final Date lastModification) throws IOException;

	public interface FileEmitter {
		void emit(WIN32_FIND_DATA win32FindData);
	}

	public interface StreamEmitter {
		void emit(StreamInfo info);
	}
}