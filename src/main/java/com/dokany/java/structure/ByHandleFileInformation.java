package com.dokany.java.structure;

public class ByHandleFileInformation extends FileInfo {

	ByHandleFileInformation(final Builder builder, final int numberOfLinks, final int volumeSerialNumber) {
		super(builder);

		dwNumberOfLinks = numberOfLinks;
		dwVolumeSerialNumber = volumeSerialNumber;
	}

	public ByHandleFileInformation() {
	}

	// public ByHandleFileInformation(final int fileAttribute, final long
	// creationTime, final long lastAccessTime, final long lastWriteTime, final
	// int volumeSerialNumber, final long fileSize, final int numberOfLinks,
	// final long fileIndex) {
	// super(null, fileSize, FileAttribute.fromInt(fileAttribute), new
	// FileTime(creationTime), new FileTime(lastAccessTime), new
	// FileTime(lastWriteTime), fileIndex);
	// }
}
