/*
  JDokan : Java library for Dokan

  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/

  http://decas-dev.net/en

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 3 of the License, or (at your option) any
later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along
with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package net.decasdev.dokan;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * FILETIME. Contains a 64-bit value representing the number of 100-nanosecond intervals since January 1,
 * 1601 (UTC).
 */
public class FileTimeUtils {
    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long DIFF = 11644473600000L;
    
	public static long toFileTime(Date date) {
		return (date.getTime() + DIFF) * 10000;
	}

	public static Date toDate(long fileTime) {
		long ms = fileTime / 10000;
        long day = ms / DAY;
        ms -= day * DAY;
        long hour = ms / HOUR;
        ms -= hour * HOUR;
        long minute = ms / MINUTE;
        ms -= minute * MINUTE;
        long second = ms / SECOND;
        ms -= second * SECOND;

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(1601, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, (int)day);
        calendar.add(Calendar.HOUR_OF_DAY, (int)hour);
        calendar.add(Calendar.MINUTE, (int)minute);
        calendar.add(Calendar.SECOND, (int)second);
        calendar.add(Calendar.MILLISECOND, (int)ms);

        return calendar.getTime();
	}
}
