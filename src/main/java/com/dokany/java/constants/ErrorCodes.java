package com.dokany.java.constants;

public enum ErrorCodes {
	ERROR_SUCCESS(0),
    // ERROR_FILE_NOT_FOUND(2;
	ERROR_FILE_NOT_FOUND(0xc0000034),
	ERROR_WRITE_FAULT(29),
	ERROR_READ_FAULT(30),
	ERROR_ALREADY_EXISTS(183),
	ERROR_FILE_EXISTS(80),
	ObjectNameCollision(0xc0000035);

	public final long val;

	private ErrorCodes(final long val) {
		this.val = val;
	}
}
