/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2015 Simon Herter <sim.herter@gmail.com>
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
package com.github.dokandev.dokanjava.util;

import java.util.Arrays;
import java.util.Optional;

public enum DokanStatus {

	SUCCESS(0), ERROR(-1), DRIVE_LETTER_ERROR(-2), DRIVER_INSTALL_ERROR(-3), START_ERROR(-4),
	MOUNT_ERROR(-5), MOUNT_POINT_ERROR(-6), UNKNOWN(Integer.MAX_VALUE);

	private final int exitCode;

	private DokanStatus(int exitCode) {
		this.exitCode = exitCode;
	}
	
	public int code() {
	  return exitCode;
	}

	public static DokanStatus fromInt(int exitCode) {
		Optional<DokanStatus> status = Arrays.stream(DokanStatus.values())
				.filter(s -> s.exitCode == exitCode).findAny();
		if (status.isPresent()) {
			return status.get();
		} else {
			return DokanStatus.UNKNOWN;
		}
	}

}
