package com.dokany.java.structure;

import static com.dokany.java.constants.FileSystemFeatures.CASE_PRESERVED_NAMES;

import com.dokany.java.constants.FileSystemFeatures;

public class VolumeInformation {

	private final int maxComponentLength;
	private final int serialNumber;
	private final String volumeName;
	private final String fileSystemName;
	private final FileSystemFeatures fileSystemFeatures;

	public VolumeInformation(
	        final int maxComponentLength,
	        final String volumeName,
	        final int serialNumber,
	        final String fileSystemName,
	        final FileSystemFeatures fileSystemFeatures) {
		this.maxComponentLength = maxComponentLength;
		this.volumeName = volumeName;
		this.serialNumber = serialNumber;
		this.fileSystemName = fileSystemName;
		this.fileSystemFeatures = fileSystemFeatures;
	}

	public VolumeInformation() {
		this(256, "VOLUME", 0x12345678, "DOKANY", CASE_PRESERVED_NAMES);
	}

	public VolumeInformation(final String volumeName, final int serialNumber, final String fileSystemName) {
		this(256, volumeName, serialNumber, fileSystemName, CASE_PRESERVED_NAMES);
	}

	public int getMaxComponentLength() {
		return maxComponentLength;
	}

	/**
	 * Default is 0x00000000
	 *
	 * @return
	 */
	public int getVolumeSerialNumber() {
		return serialNumber;
	}

	/**
	 * Default is VOLUME;
	 *
	 * @return
	 */
	public String getVolumeName() {
		return volumeName;
	}

	/**
	 * Default is DOKANY.
	 *
	 * @return
	 */
	public String getFileSystemName() {
		return fileSystemName;
	}

	/**
	 * Default is FileSystemFeatures.CasePreservedNames
	 *
	 * @return
	 */
	public FileSystemFeatures getFileSystemFeatures() {
		return fileSystemFeatures;
	}

	@Override
	public String toString() {
		return "{maxComponentLength: " + maxComponentLength + System.lineSeparator()
		        + "   serialNumber: " + serialNumber + System.lineSeparator()
		        + "   volumeName: " + volumeName + System.lineSeparator()
		        + "   fileSystemName: " + fileSystemName + System.lineSeparator()
		        + "   fileSystemFeatures: " + fileSystemFeatures
		        + "}";
	}
}
