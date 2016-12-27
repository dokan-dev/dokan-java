package com.dokany.java.constants;

public enum CreationDisposition {
	CREATE_NEW(1, "Create New"),
	CREATE_ALWAYS(2, "Create Always"),
	OPEN_EXISTING(3, "Open Existing"),
	OPEN_ALWAYS(4, "Open Always"),
	TRUNCATE_EXISTING(5, "Truncate Existing");

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