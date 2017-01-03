package com.dokany.java.constants;

public enum FileSystemFeatures {
	NONE(0),
	CASE_SENSITIVE_SEARCH(1),
	CASE_PRESERVED_NAMES(2),
	UNICODE_ON_DISK(4),
	PERSISTENT_ACLS(8),
	VOLUME_QUOTAS(0x20),
	SUPPORTS_SPARSE_FILES(0x40),
	SUPPORTS_REPARSE_POINTS(0x80),
	SUPPORTS_REMOTE_STORAGE(0x100),
	VOLUME_IS_COMPRESSED(0x00008000),
	SUPPORTS_OBJECT_IDS(0x00010000),
	SUPPORTS_ENCRYPTION(0x00020000),
	NAMED_STREAMS(0x00040000),
	READ_ONLY_VOLUME(0x00080000),
	SEQUENTIAL_WRITE_ONCE(0x00100000),
	SUPPORTS_TRANSACTIONS(0x00200000);

	public final int val;

	private FileSystemFeatures(final int val) {
		this.val = val;
	}

	public final static FileSystemFeatures fromInt(final int value) {
		for (final FileSystemFeatures current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for FileSystemFeatures");
	}
}
