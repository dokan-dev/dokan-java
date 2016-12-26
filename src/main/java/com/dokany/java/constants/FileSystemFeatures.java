package com.dokany.java.constants;

public enum FileSystemFeatures {
	NONE(0),
	CaseSensitiveSearch(1),
	CasePreservedNames(2),
	UnicodeOnDisk(4),
	PersistentAcls(8),
	VolumeQuotas(0x20),
	SupportsSparseFiles(0x40),
	SupportsReparsePoints(0x80),
	SupportsRemoteStorage(0x100),
	VolumeIsCompressed(0x00008000),
	SupportsObjectIDs(0x00010000),
	SupportsEncryption(0x00020000),
	NamedStreams(0x00040000),
	ReadOnlyVolume(0x00080000),
	SequentialWriteOnce(0x00100000),
	SupportsTransactions(0x00200000);

	public final int val;

	private FileSystemFeatures(final int val) {
		this.val = val;
	}

	public static FileSystemFeatures fromInt(final int value) {
		for (final FileSystemFeatures current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for FileSystemFeatures");
	}
}
