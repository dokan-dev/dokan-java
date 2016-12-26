package com.dokany.java;

import com.dokany.java.constants.ErrorCodes;
import com.dokany.java.constants.WinError;

public class DokanyException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 217360542205521310L;
	public final int val;

	public DokanyException(final long errorCode) {
		if ((errorCode < 0) || (errorCode > 4294967295L)) {
			throw new IllegalArgumentException("error code is not in range [0, 4294967295]");
		}
		val = (int) errorCode;
	}

	public DokanyException(final WinError errorCode) {
		this(errorCode.val);
	}

	public DokanyException(final ErrorCodes errorCode) {
		this(errorCode.val);
	}
}
