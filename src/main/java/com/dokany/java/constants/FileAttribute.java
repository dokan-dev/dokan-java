package com.dokany.java.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyUtils;
import com.dokany.java.structure.EnumIntegerSet;
import com.sun.jna.platform.win32.WinNT;

/**
 *
 * @see {@linkplain https://msdn.microsoft.com/en-us/library/gg258117(v=vs.85).aspx}
 *
 */
public enum FileAttribute implements EnumInteger {

	ARCHIVE(WinNT.FILE_ATTRIBUTE_ARCHIVE),

	COMPRESSED(WinNT.FILE_ATTRIBUTE_COMPRESSED),

	DEVICE(WinNT.FILE_ATTRIBUTE_DEVICE),

	DIRECTORY(WinNT.FILE_ATTRIBUTE_DIRECTORY),

	ENCRYPTED(WinNT.FILE_ATTRIBUTE_ENCRYPTED),

	HIDDEN(WinNT.FILE_ATTRIBUTE_HIDDEN),

	INTEGRITY_STREAM(0x8000),

	NORMAL(WinNT.FILE_ATTRIBUTE_NORMAL),

	NOT_CONTENT_INDEXED(WinNT.FILE_ATTRIBUTE_NOT_CONTENT_INDEXED),

	NO_SCRUB_DATA(0x20000),

	OFFLINE(WinNT.FILE_ATTRIBUTE_OFFLINE),

	READONLY(WinNT.FILE_ATTRIBUTE_READONLY),

	REPARSE_POINT(WinNT.FILE_ATTRIBUTE_REPARSE_POINT),

	SPARSE_FILE(WinNT.FILE_ATTRIBUTE_SPARSE_FILE),

	SYSTEM(WinNT.FILE_ATTRIBUTE_SYSTEM),

	TEMPORARY(WinNT.FILE_ATTRIBUTE_TEMPORARY),

	VIRTUAL(WinNT.FILE_ATTRIBUTE_VIRTUAL);

	private final static Logger LOGGER = LoggerFactory.getLogger(FileAttribute.class);

	private final int mask;

	@Override
	public int mask() {
		return mask;
	}

	private FileAttribute(final int i) {
		mask = i;
	}

	public static EnumIntegerSet<FileAttribute> fromInt(final int value) {
		return DokanyUtils.enumSetFromInt(value, values());
	}
}
