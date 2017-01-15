package com.dokany.java.constants;

import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_ARCHIVE;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_COMPRESSED;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_DEVICE;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_DIRECTORY;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_ENCRYPTED;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_HIDDEN;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NORMAL;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NOT_CONTENT_INDEXED;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_OFFLINE;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_READONLY;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_REPARSE_POINT;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_SPARSE_FILE;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_SYSTEM;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_TEMPORARY;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_VIRTUAL;

import com.dokany.java.Utils;
import com.sun.jna.ptr.IntByReference;

public enum FileAttribute implements EnumInteger {

	ARCHIVE(FILE_ATTRIBUTE_ARCHIVE),

	COMPRESSED(FILE_ATTRIBUTE_COMPRESSED),

	DEVICE(FILE_ATTRIBUTE_DEVICE),

	DIRECTORY(FILE_ATTRIBUTE_DIRECTORY),

	ENCRYPTED(FILE_ATTRIBUTE_ENCRYPTED),

	HIDDEN(FILE_ATTRIBUTE_HIDDEN),

	INTEGRITY_STREAM(0x8000),

	NORMAL(FILE_ATTRIBUTE_NORMAL),

	NOT_CONTENT_INDEXED(FILE_ATTRIBUTE_NOT_CONTENT_INDEXED),

	NO_SCRUB_DATA(0x20000),

	OFFLINE(FILE_ATTRIBUTE_OFFLINE),

	READONLY(FILE_ATTRIBUTE_READONLY),

	REPARSE_POINT(FILE_ATTRIBUTE_REPARSE_POINT),

	SPARSE_FILE(FILE_ATTRIBUTE_SPARSE_FILE),

	SYSTEM(FILE_ATTRIBUTE_SYSTEM),

	TEMPORARY(FILE_ATTRIBUTE_TEMPORARY),

	VIRTUAL(FILE_ATTRIBUTE_VIRTUAL);

	public final int val;

	@Override
	public int getVal() {
		return val;
	}

	private FileAttribute(final int i) {
		val = i;
	}

	public final static int MASK;
	static {
		MASK = READONLY.val | HIDDEN.val | SYSTEM.val
		        | DIRECTORY.val | ARCHIVE.val | DEVICE.val
		        | NORMAL.val | TEMPORARY.val | SPARSE_FILE.val
		        | REPARSE_POINT.val | COMPRESSED.val | OFFLINE.val
		        | NOT_CONTENT_INDEXED.val | ENCRYPTED.val
		        | INTEGRITY_STREAM.val | NO_SCRUB_DATA.val;
	}

	public final static int fromAttributesAndFlags(final IntByReference attributesAndFlags) {
		if (Utils.isNull(attributesAndFlags)) {
			return NORMAL.val;
		}
		return (attributesAndFlags.getValue() & MASK);
	}

	public static FileAttribute fromInt(final int val) {
		return Utils.enumFromInt(val, values());
	}
}
