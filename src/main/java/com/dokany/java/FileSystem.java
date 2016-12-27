package com.dokany.java;

import static com.dokany.java.constants.FileSystemFeatures.CasePreservedNames;

import java.io.IOException;
import java.util.Date;

import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.FileInfo;
import com.sun.jna.WString;

/**
 * This should be extended by file system providers.
 *
 * @param <TItem>
 */
public interface FileSystem<TItem> {

	final FileHandleStore fileHandles = new FileHandleStore();

	public default boolean isDefaultLog() {
		return isDebug();
	}

	public default boolean isDebug() {
		return false;
	}

	public default boolean isDebugStderrOutput() {
		return false;
	}

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
		// return 0x12345678;
		return 0x00000000;
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

	public default int getSecurity(final FileHandle<TItem> handle, final int kind, final byte[] out) {
		return 0;
	}

	public void setSecurity(final FileHandle<TItem> handleHandle, final int kind, final byte[] data);

	/**
	 * Default is FileSystemFeatures.CasePreservedNames
	 *
	 * @return
	 */
	public default int getFileSystemFeatures() {
		return CasePreservedNames.val;
	}

	public FileInfo getFileInformation(final FileHandle<TItem> handle) throws IOException;

	public default FileHandle<TItem> getHandle(final WString fileName, final long id) throws IOException {
		return getHandle(fileName.toString(), id);
	}

	public FileHandle<TItem> getHandle(final String fileName, final long id) throws IOException;

	public FileHandle<TItem> createHandle(final String fileName) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public TItem createFile(final String fileName,
	        CreationDisposition disposition,
	        long options,
	        final boolean isDirectory,
	        final FileAttribute... attributes) throws IOException;

	public TItem findExisting(final String fileName, final boolean isDirectory) throws IOException;

	public void cleanup(final FileHandle<TItem> handle);

	public void close(final FileHandle<TItem> handle);

	public void mounted();

	public void unmounted();

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void unlock(final FileHandle<TItem> handle, final long byteOffset, final long length);

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void lock(final FileHandle<TItem> handle, final long byteOffset, final long length);

	public void setAllocationSize(final FileHandle<TItem> handle, final long length);

	public void setEndOfFile(final FileHandle<TItem> handle, final long byteOffset);

	public void move(final FileHandle<TItem> oldHandle, final FileHandle<TItem> newHandle, final boolean replaceIfExisting) throws IOException;

	public void deleteFile(final FileHandle<TItem> handle) throws IOException;

	public void deleteDirectory(final FileHandle<TItem> handle) throws IOException;

	public int read(final FileHandle<TItem> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public int write(final FileHandle<TItem> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public long truncate(final FileHandle<TItem> handle) throws IOException;

	public void findFiles(final FileHandle<TItem> handle, final FileEmitter emitter) throws IOException;

	public void findStreams(final FileHandle<TItem> handle, final StreamEmitter emitter) throws IOException;

	public void flushFileBuffers(final FileHandle<TItem> handle);

	public void setAttributes(final FileHandle<TItem> handle, final int attributes);

	public void setTime(final FileHandle<TItem> handle, final Date creation, final Date access, final Date modification);

	public interface FileEmitter {
		void emit(FileInfo info);
	}

	public interface StreamEmitter {
		void emit(StreamInfo info);
	}
}