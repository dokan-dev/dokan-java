package com.dokany.java;

import java.io.IOException;

import com.dokany.java.constants.ErrorCode;
import com.dokany.java.constants.WinError;

public class DokanyException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = -862591089502909563L;
	/**
	*
	*/
	public final int val;

	public DokanyException(final long errorCode, final IOException exception) {
		if ((errorCode < 0) || (errorCode > 4294967295L)) {
			throw new IllegalArgumentException("error code (" + errorCode + ") is not in range [0, 4294967295]", exception);
		}
		val = (int) errorCode;
	}

	public DokanyException(final WinError errorCode, final IOException exception) {
		this(errorCode.val, exception);
	}

	public DokanyException(final ErrorCode errorCode, final IOException exception) {
		this(errorCode.val, exception);
	}
}