package com.dokany.java.constants;

import com.sun.jna.platform.win32.WinNT;

public enum CreationDisposition {
	CREATE_NEW(WinNT.CREATE_NEW, "Create New"),
	CREATE_ALWAYS(WinNT.CREATE_ALWAYS, "Create Always"),
	OPEN_EXISTING(WinNT.OPEN_EXISTING, "Open Existing"),
	OPEN_ALWAYS(WinNT.OPEN_ALWAYS, "Open Always"),
	TRUNCATE_EXISTING(WinNT.TRUNCATE_EXISTING, "Truncate Existing");

	public final int val;
	public final String name;
	public final boolean isReadonly;

	private CreationDisposition(final int val, final String name) {
		this.val = val;
		this.name = name;
		isReadonly = ((val == 3) || (val == 4));
	}

	public final static CreationDisposition fromInt(final int value) {
		for (final CreationDisposition current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for CreationDisposition");
	}
}