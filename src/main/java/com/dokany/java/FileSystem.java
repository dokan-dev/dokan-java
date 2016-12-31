package com.dokany.java;

import static com.dokany.java.constants.FileSystemFeatures.CasePreservedNames;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

/**
 * This should be extended by file system providers.
 *
 * @param <TNode>
 */
public interface FileSystem {

	public default boolean isDefaultLog() {
		return isDebug();
	}

	public default boolean isDebug() {
		return false;
	}

	public default boolean isDebugStderrOutput() {
		return false;
	}

	public String getRootPath();

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

	public default int getSecurity(final String path, final int kind, final byte[] out) throws IOException {
		return 0;
	}

	public void setSecurity(final String path, final int kind, final byte[] data) throws IOException;

	/**
	 * Default is FileSystemFeatures.CasePreservedNames
	 *
	 * @return
	 */
	public default int getFileSystemFeatures() {
		return CasePreservedNames.val;
	}

	public ByHandleFileInfo getInfo(final String path) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public void createEmptyFile(
	        final String path,
	        long options,
	        final FileAttribute attributes) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public void createEmptyDirectory(
	        final String path,
	        final long options,
	        final FileAttribute attributes)
	        throws IOException;

	public boolean pathExists(final String path) throws IOException;

	public Set<WIN32_FIND_DATA> findFiles(final String path) throws IOException;

	public Set<WIN32_FIND_DATA> findFilesWithPattern(final String path, final String pattern) throws IOException;

	public Set<Win32FindStreamData> findStreams(final String path) throws IOException;

	public void mounted() throws IOException;

	public void unmounted() throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void unlock(final String path, final int offset, final int length) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public void lock(final String path, final int offset, final int length) throws IOException;

	public void move(final String oldPath, final String newPath, final boolean replaceIfExisting) throws IOException;

	public void deleteFile(final String path) throws IOException;

	public void deleteDirectory(final String path) throws IOException;

	public int read(final String path, final int offset, final byte[] data, final int readLength) throws IOException;

	public int write(final String path, final int offset, final byte[] data, final int writeLength) throws IOException;

	public void flushFileBuffers(final String path) throws IOException;

	public void cleanup(final String path) throws IOException;

	public void close(final String path) throws IOException;

	public long truncate(final String path) throws IOException;

	public void setAllocationSize(final String path, final int length) throws IOException;

	public void setEndOfFile(final String path, final int offset) throws IOException;

	public void setAttributes(final String path, final FileAttribute attributes);

	public void setTime(final String path, final FILETIME creation, final FILETIME lastAccess, final FILETIME lastModification) throws IOException;
}