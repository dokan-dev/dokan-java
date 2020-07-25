package dev.dokan.dokan_java.wrappers;


import dev.dokan.dokan_java.DokanOperations;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;

/**
 * Supplementary class to bundle information of the mounted volume and its filesystem.
 * <p>Mainly used for {@link DokanOperations#GetVolumeInformation} function to have all needed information at one place.</p>
 * TODO: Maybe this can be completely integrated in the DokanFileSystem class
 */
public final class VolumeInformation {
	private final int maxComponentLength;
	private final String name;
	private final int serialNumber;
	private final String fileSystemName;
	private final MaskValueSet<FileSystemFlag> fileSystemFeatures;

	public static final int DEFAULT_MAX_COMPONENT_LENGTH = 256;
	public static final int DEFAULT_SERIAL_NUMBER = 305419896;
	public static final String DEFAULT_VOLUME_NAME = "VOLUME1";
	public static final String DEFAULT_FS_NAME = "DOKANY";
	public static final MaskValueSet<FileSystemFlag> DEFAULT_FS_FEATURES = MaskValueSet.emptySet(FileSystemFlag.class);

	static {
		DEFAULT_FS_FEATURES.add(FileSystemFlag.CASE_PRESERVED_NAMES);
	}

	/**
	 * Provides default values for maxComponentLength and fileSystemFeatures.
	 *
	 * @param volumeName
	 * @param serialNumber
	 * @param fileSystemName
	 */
	public VolumeInformation(final String volumeName, final int serialNumber, final String fileSystemName) {
		this(DEFAULT_MAX_COMPONENT_LENGTH, volumeName, serialNumber, fileSystemName, DEFAULT_FS_FEATURES);
	}

	/**
	 * Provides default values for all values.
	 */
	public VolumeInformation() {
		this(DEFAULT_MAX_COMPONENT_LENGTH, DEFAULT_VOLUME_NAME, DEFAULT_SERIAL_NUMBER, DEFAULT_FS_NAME, DEFAULT_FS_FEATURES);
	}

	public int getMaxComponentLength() {
		return this.maxComponentLength;
	}

	public String getName() {
		return this.name;
	}

	public int getSerialNumber() {
		return this.serialNumber;
	}

	public String getFileSystemName() {
		return this.fileSystemName;
	}

	public MaskValueSet<FileSystemFlag> getFileSystemFeatures() {
		return this.fileSystemFeatures;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof VolumeInformation)) return false;
		final VolumeInformation other = (VolumeInformation) o;
		if (this.getMaxComponentLength() != other.getMaxComponentLength()) return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
		if (this.getSerialNumber() != other.getSerialNumber()) return false;
		final Object this$fileSystemName = this.getFileSystemName();
		final Object other$fileSystemName = other.getFileSystemName();
		if (this$fileSystemName == null ? other$fileSystemName != null : !this$fileSystemName.equals(other$fileSystemName)) return false;
		final Object this$fileSystemFeatures = this.getFileSystemFeatures();
		final Object other$fileSystemFeatures = other.getFileSystemFeatures();
		if (this$fileSystemFeatures == null ? other$fileSystemFeatures != null : !this$fileSystemFeatures.equals(other$fileSystemFeatures)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		result = result * PRIME + this.getMaxComponentLength();
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		result = result * PRIME + this.getSerialNumber();
		final Object $fileSystemName = this.getFileSystemName();
		result = result * PRIME + ($fileSystemName == null ? 43 : $fileSystemName.hashCode());
		final Object $fileSystemFeatures = this.getFileSystemFeatures();
		result = result * PRIME + ($fileSystemFeatures == null ? 43 : $fileSystemFeatures.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "VolumeInformation(maxComponentLength=" + this.getMaxComponentLength() + ", name=" + this.getName() + ", serialNumber=" + this.getSerialNumber() + ", fileSystemName=" + this.getFileSystemName() + ", fileSystemFeatures=" + this.getFileSystemFeatures() + ")";
	}

	public VolumeInformation(final int maxComponentLength, final String name, final int serialNumber, final String fileSystemName, final MaskValueSet<FileSystemFlag> fileSystemFeatures) {
		this.maxComponentLength = maxComponentLength;
		this.name = name;
		this.serialNumber = serialNumber;
		this.fileSystemName = fileSystemName;
		this.fileSystemFeatures = fileSystemFeatures;
	}
}
