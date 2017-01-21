package com.dokany.java;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.MountOption;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.FullFileInfo;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * This should be extended by file system providers.
 */
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class DokanyFileSystem {

	@NonNull
	VolumeInformation volumeInfo;
	@NonNull
	FreeSpace freeSpace;
	long allocationUnitSize;
	long sectorSize;
	long timeout;
	@NonNull
	Date rootCreationDate;
	@NonNull
	String root;
	boolean isDebug;
	boolean isDebugStdErr;

	public DokanyFileSystem(
	        @NonNull final DeviceOptions deviceOptions,
	        @NonNull final VolumeInformation volumeInfo,
	        @NonNull final FreeSpace freeSpace,
	        @NonNull final Date rootCreationDate,
	        @NonNull final String rootPath) {
		this.volumeInfo = volumeInfo;

		this.freeSpace = freeSpace;

		timeout = deviceOptions.Timeout;
		allocationUnitSize = deviceOptions.AllocationUnitSize;
		sectorSize = deviceOptions.SectorSize;
		this.rootCreationDate = rootCreationDate;

		root = DokanyUtils.normalize(rootPath);
		isDebug = deviceOptions.getMountOptions().contains(MountOption.DEBUG_MODE);
		isDebugStdErr = deviceOptions.getMountOptions().contains(MountOption.STD_ERR_OUTPUT);
	}

	public abstract void mounted() throws IOException;

	public abstract void unmounted() throws IOException;

	public abstract boolean doesPathExist(@NonNull final String path) throws IOException;

	public abstract Set<WIN32_FIND_DATA> findFilesWithPattern(@NonNull final String pathToSearch, final String pattern) throws IOException;

	public abstract Set<Win32FindStreamData> findStreams(@NonNull final String pathToSearch) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public abstract void unlock(@NonNull final String path, final int offset, final int length) throws IOException;

	/**
	 * Only used if dokan option UserModeLock is enabled
	 */
	public abstract void lock(@NonNull final String path, final int offset, final int length) throws IOException;

	public abstract void move(@NonNull final String oldPath, @NonNull final String newPath, final boolean replaceIfExisting) throws IOException;

	public abstract void deleteFile(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException;

	public abstract void deleteDirectory(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException;

	public abstract FileData read(@NonNull final String path, final int offset, final int readLength) throws IOException;

	public abstract int write(@NonNull final String path, final int offset, final byte[] data, final int writeLength) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public abstract void createEmptyFile(final String path, long options, final EnumIntegerSet<FileAttribute> attributes) throws IOException;

	// TODO: Add SecurityContext and ShareAccess and DesiredAccess
	public abstract void createEmptyDirectory(@NonNull final String path, final long options, @NonNull final EnumIntegerSet<FileAttribute> attributes) throws IOException;

	public abstract void flushFileBuffers(@NonNull final String path) throws IOException;

	public abstract void cleanup(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException;

	public abstract void close(@NonNull final String path, @NonNull final DokanyFileInfo dokanyFileInfo) throws IOException;

	public abstract int getSecurity(@NonNull final String path, final int kind, @NonNull final byte[] out) throws IOException;

	public abstract void setSecurity(@NonNull final String path, final int kind, @NonNull final byte[] data) throws IOException;

	public abstract long truncate(@NonNull final String path) throws IOException;

	public abstract void setAllocationSize(@NonNull final String path, final int length) throws IOException;

	public abstract void setEndOfFile(@NonNull final String path, final int offset) throws IOException;

	public abstract void setAttributes(@NonNull final String path, @NonNull final EnumIntegerSet<FileAttribute> attributes) throws IOException;

	public abstract FullFileInfo getInfo(@NonNull final String path) throws IOException;

	public abstract void setTime(@NonNull final String path, @NonNull final FILETIME creation, @NonNull final FILETIME lastAccess, @NonNull final FILETIME lastModification) throws IOException;
}