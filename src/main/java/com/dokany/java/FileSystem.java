package com.dokany.java;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.FullFileInfo;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

/**
 * This should be extended by file system providers.
 */
public abstract class FileSystem {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileSystem.class);

	protected final VolumeInformation volumeInfo;
	protected final FreeSpace freeSpace;
	protected final long allocationUnitSize;
	protected final long sectorSize;
	protected final long timeout;
	protected final Date rootCreationDate;
	protected final String rootPath;

	public FileSystem(
	        @NotNull final DeviceOptions deviceOptions,
	        @NotNull final VolumeInformation volumeInfo,
	        @NotNull final FreeSpace freeSpace,
	        @NotNull final Date rootCreationDate,
	        @NotNull final String rootPath) {
		this.volumeInfo = volumeInfo;

		this.freeSpace = freeSpace;

		timeout = deviceOptions.Timeout;
		allocationUnitSize = deviceOptions.AllocationUnitSize;
		sectorSize = deviceOptions.SectorSize;
		this.rootCreationDate = rootCreationDate;
		this.rootPath = rootPath;
	}

	public final VolumeInformation getVolumeInfo() {
		return volumeInfo;
	}

	public final FreeSpace getFreeSpace() {
		return freeSpace;
	}

	/**
	 * Default is 4096.
	 *
	 * @return
	 */
	public long getAllocationUnitSize() {
		return allocationUnitSize;
	}

	/**
	 * Default is 4096.
	 *
	 * @return
	 */
	public long getSectorSize() {
		return sectorSize;
	}

	/**
	 * Default is 10000.
	 *
	 * @return
	 */
	public final long getTimeout() {
		return timeout;
	}

	public final String getRootPath() {
		return rootPath;
	};

	public final Date getRootCreateDate() {
		return rootCreationDate;
	}

	public boolean isDebugStderrOutput() {
		return false;
	}

	public boolean isDefaultLog() {
		return isDebug();
	}

	public boolean isDebug() {
		return false;
	}

	public abstract void mounted() throws IOException;

	public abstract void unmounted() throws IOException;

	public abstract boolean doesPathExist(final String path) throws IOException;

	public abstract Set<WIN32_FIND_DATA> findFiles(final String path) throws IOException;

	public abstract Set<WIN32_FIND_DATA> findFilesWithPattern(final String path, final String pattern) throws IOException;

	public abstract Set<Win32FindStreamData> findStreams(final String path) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public abstract void unlock(final String path, final int offset, final int length) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public abstract void lock(final String path, final int offset, final int length) throws IOException;

	public abstract void move(final String oldPath, final String newPath, final boolean replaceIfExisting) throws IOException;

	public abstract void deleteFile(final String path) throws IOException;

	public abstract void deleteDirectory(final String path) throws IOException;

	public abstract FileData read(final String path, final int offset, final int readLength) throws IOException;

	public abstract int write(final String path, final int offset, final byte[] data, final int writeLength) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public abstract void createEmptyFile(
	        final String path,
	        long options,
	        final FileAttribute attributes) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public abstract void createEmptyDirectory(
	        final String path,
	        final long options,
	        final FileAttribute attributes)
	        throws IOException;

	public abstract void flushFileBuffers(final String path) throws IOException;

	public abstract void cleanup(final String path) throws IOException;

	public abstract void close(final String path) throws IOException;

	public abstract int getSecurity(final String path, final int kind, final byte[] out) throws IOException;

	public abstract void setSecurity(final String path, final int kind, final byte[] data) throws IOException;

	public abstract long truncate(final String path) throws IOException;

	public abstract void setAllocationSize(final String path, final int length) throws IOException;

	public abstract void setEndOfFile(final String path, final int offset) throws IOException;

	public abstract void setAttributes(final String path, final FileAttribute attributes) throws IOException;

	public abstract FullFileInfo getInfo(final String path) throws IOException;

	public abstract void setTime(final String path, final FILETIME creation, final FILETIME lastAccess, final FILETIME lastModification) throws IOException;
}