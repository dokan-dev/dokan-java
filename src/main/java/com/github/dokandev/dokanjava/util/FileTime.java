/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/ 2015 Simon Herter
 * <sim.herter@gmail.com>
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

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.google.common.math.LongMath;

/**
 * FILETIME. Contains a 64-bit value representing the number of 100-nanosecond intervals since
 * January 1, 1601 (UTC).
 */
public class FileTime {

  static final long EPOCH_DIFF = 11644473600L; // in seconds

  public static long toFileTime(ZonedDateTime date) {
    long secondsSince1970 = date.toEpochSecond();
    long secondsSince1601 = secondsSince1970 + EPOCH_DIFF;
    long hundredNanoSecondsSince1601 = secondsSince1601 * (10 * 1000 * 1000);
	return hundredNanoSecondsSince1601 + (date.getNano() / 100);
	}

  public static ZonedDateTime toDate(long fileTime) {
    long secondsSince1601 = Math.floorDiv(fileTime, (10 * 1000 * 1000));
    long secondsSince1970 = secondsSince1601 - EPOCH_DIFF;
    int nanoSecondsOffset= LongMath.mod(fileTime, (10 * 1000 * 1000)) * 100;
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(secondsSince1970, nanoSecondsOffset), ZoneOffset.UTC);
  }
}
