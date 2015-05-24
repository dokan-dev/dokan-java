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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * FILETIME. Contains a 64-bit value representing the number of 100-nanosecond intervals since
 * January 1, 1601 (UTC).
 */
public class FileTime {

  private static final long EPOCH_DIFF = 11644473600000L;

  public static long toFileTime(LocalDateTime date) {
    long milliSecondsSince1970 = date.atZone(ZoneOffset.UTC).toEpochSecond();
    long milliSecondsSince1601 = milliSecondsSince1970 + EPOCH_DIFF;
    long hundredNanoSecondsSince1601 = milliSecondsSince1601 * (10 * 1000);
	return hundredNanoSecondsSince1601 + (date.getNano() / 100);
	}

  public static LocalDateTime toDate(long fileTime) {
    long milliSecondsSince1601 = fileTime / (10 * 1000); // 10 -> µs, 1000 -> ms
    long milliSecondsSince1970 = milliSecondsSince1601 - EPOCH_DIFF;
    int nanoSecondsOffset= (int) (milliSecondsSince1601 % (10 * 1000)) * 100;
    return LocalDateTime.ofEpochSecond(milliSecondsSince1970, nanoSecondsOffset, ZoneOffset.UTC);
  }
}
