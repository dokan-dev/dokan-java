package com.dokan.java;

import com.dokan.java.constants.microsoft.FileSystemFlag;
import com.dokan.java.structure.EnumIntegerSet;

/**
 * Supplementary class to bundle information of the filesystem.
 * <p>Mainly used for {@link DokanyOperations#GetVolumeInformation} function to have all needed information at one place.</p>
 */
public final class FileSystemInformation {

	public static final int DEFAULT_MAX_COMPONENT_LENGTH = 256;
	public static final String DEFAULT_FS_NAME = "NTFS";

	private final int maxComponentLength;
	private final String fileSystemName;
	private final EnumIntegerSet<FileSystemFlag> fileSystemFeatures;

	/**
	 * Provides default values for maxComponentLength and Filesystem name.
     * TODO: add checks to assert filesystemflags is not null!
	 */
	public FileSystemInformation(EnumIntegerSet<FileSystemFlag> fileSystemFlags) {
		this(DEFAULT_MAX_COMPONENT_LENGTH, DEFAULT_FS_NAME, fileSystemFlags);
	}

    public FileSystemInformation(final int maxComponentLength, final String fileSystemName, final EnumIntegerSet<FileSystemFlag> fileSystemFeatures) {
        this.maxComponentLength = maxComponentLength;
        this.fileSystemName = fileSystemName;
        this.fileSystemFeatures = fileSystemFeatures;
    }

	public int getMaxComponentLength() {
		return this.maxComponentLength;
	}

	public String getFileSystemName() {
		return this.fileSystemName;
	}

	public EnumIntegerSet<FileSystemFlag> getFileSystemFeatures() {
		return this.fileSystemFeatures;
	}

	@Override
	public String toString() {
		return "VolumeInformation(maxComponentLength=" + this.getMaxComponentLength() + ", fileSystemName=" + this.getFileSystemName() + ", fileSystemFeatures=" + this.getFileSystemFeatures() + ")";
	}

}
