package com.dokany.java.constants;

public enum FileAttribute {
	FILE_ATTRIBUTE_ARCHIVE(0x20),
	FILE_ATTRIBUTE_COMPRESSED(0x800),
	FILE_ATTRIBUTE_DEVICE(0x40),
	FILE_ATTRIBUTE_DIRECTORY(0x10),
	FILE_ATTRIBUTE_ENCRYPTED(0x4000),
	FILE_ATTRIBUTE_HIDDEN(0x2),
	FILE_ATTRIBUTE_INTEGRITY_STREAM(0x8000),
	FILE_ATTRIBUTE_NORMAL(0x80),
	FILE_ATTRIBUTE_NOT_CONTENT_INDEXED(0x2000),
	FILE_ATTRIBUTE_NO_SCRUB_DATA(0x20000),
	FILE_ATTRIBUTE_OFFLINE(0x1000),
	FILE_ATTRIBUTE_READONLY(0x1),
	FILE_ATTRIBUTE_REPARSE_POINT(0x400),
	FILE_ATTRIBUTE_SPARSE_FILE(0x200),
	FILE_ATTRIBUTE_SYSTEM(0x4),
	FILE_ATTRIBUTE_TEMPORARY(0x100),
	FILE_ATTRIBUTE_VIRTUAL(0x10000);

	public final int val;

	private FileAttribute(final int i) {
		val = i;
	}

	public static FileAttribute fromInt(final int value) {
		for (final FileAttribute current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for FileAttribute");
	}

	public static int fromAttributes(final FileAttribute... attributes) {
		int toReturn = 0;
		for (final FileAttribute current : attributes) {
			toReturn |= current.val;
		}
		return toReturn;
	}
}
