package dev.dokan.dokan_java;

import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;

/**
 * Supplementary class to bundle information of the filesystem.
 * <p>Mainly used for {@link DokanOperations#GetVolumeInformation} function to have all needed information at one place.</p>
 */
public final class FileSystemInformation {

    public static final int DEFAULT_MAX_COMPONENT_LENGTH = 256;
    public static final String DEFAULT_FS_NAME = "NTFS";

    private final int maxComponentLength;
    private final String fileSystemName;
    private final MaskValueSet<FileSystemFlag> fileSystemFeatures;

    /**
     * Provides default values for maxComponentLength and Filesystem name.
     *
     * @param fileSystemFlags An {@link MaskValueSet} of features the file system supports. For possible values, see the {@link FileSystemFlag} enum.
     */
    public FileSystemInformation(MaskValueSet<FileSystemFlag> fileSystemFlags) {
        this(DEFAULT_MAX_COMPONENT_LENGTH, DEFAULT_FS_NAME, fileSystemFlags);
    }

    public FileSystemInformation(final int maxComponentLength, final String fileSystemName, final MaskValueSet<FileSystemFlag> fileSystemFeatures) {
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

    public MaskValueSet<FileSystemFlag> getFileSystemFeatures() {
        return this.fileSystemFeatures;
    }

    @Override
    public String toString() {
        return "VolumeInformation(maxComponentLength=" + this.getMaxComponentLength() + ", fileSystemName=" + this.getFileSystemName() + ", fileSystemFeatures=" + this.getFileSystemFeatures() + ")";
    }

}
