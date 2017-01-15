package com.dokany.java.constants;

public enum ErrorCodes {

	SUCCESS(0),

	ERROR_WRITE_FAULT(29),

	ERROR_READ_FAULT(30),

	ERROR_FILE_NOT_FOUND(0xc0000034),

	OBJECT_NAME_COLLISION(0xc0000035),

	ERROR_FILE_EXISTS(80),

	ERROR_ALREADY_EXISTS(183);

	public final int val;

	public int getVal() {
		return val;
	}

	private ErrorCodes(final int val) {
		this.val = val;
	}
}
