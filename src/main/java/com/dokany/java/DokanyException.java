/*
 * Dokan-Java : Java library for Dokan
 *
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
 *               2015 Simon Herter <sim.herter@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

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
