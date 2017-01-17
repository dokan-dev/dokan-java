package com.dokany.java.constants;

import com.dokany.java.DokanyUtils;
import com.sun.jna.platform.win32.WinNT;

public enum CreationDisposition implements EnumInteger {
	CREATE_NEW(WinNT.CREATE_NEW, "Create New"),
	CREATE_ALWAYS(WinNT.CREATE_ALWAYS, "Create Always"),
	OPEN_EXISTING(WinNT.OPEN_EXISTING, "Open Existing"),
	OPEN_ALWAYS(WinNT.OPEN_ALWAYS, "Open Always"),
	TRUNCATE_EXISTING(WinNT.TRUNCATE_EXISTING, "Truncate Existing");

	private final int mask;

	@Override
	public int mask() {
		return mask;
	}

	private final String description;

	public String description() {
		return description;
	}

	public final boolean isReadonly;

	private CreationDisposition(final int i, final String desc) {
		mask = i;
		description = desc;

		// TODO: Is this logic correct?
		isReadonly = ((mask == 3) || (mask == 4));
	}

	public static CreationDisposition fromInt(final int value) {
		return DokanyUtils.enumFromInt(value, values());
	}
}