/*
  JDokan : Java library for Dokan

  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
  				2009 Caleido AG   http://www.wuala.com/

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

package com.github.sherter.jdokan;

import java.util.EnumSet;
import java.util.Set;

public class FileAccess {
    public enum FileAccessFlags {
        // these are the observed values that are passed to onCreateFile
        GENERIC_ALL(0x10),
        GENERIC_EXECUTE(0x20),
        GENERIC_WRITE(0x40),
        GENERIC_READ(0x80);

        private int value;

        FileAccessFlags(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Translates a numeric status code into a Set of StatusFlag enums
     * @param value
     * @return EnumSet representing a documents status
     */
    public static EnumSet<FileAccessFlags> getFlags(int value)
    {
        EnumSet<FileAccessFlags> flags = EnumSet.noneOf(FileAccessFlags.class);

        for (FileAccessFlags flag: FileAccessFlags.values()) {
            long flagValue = flag.getValue();
            if ((flagValue & value) == flagValue)
                flags.add(flag);
        }

        return flags;
    }


    /**
     * Translates a set of flags enums into a numeric status code
     * @param flags if statusFlags
     * @return numeric representation of the document status
     */
    public static long getStatusValue(Set<FileAccessFlags> flags)
    {
        long value=0;
        for (FileAccessFlags flag: flags) {
            value |= flag.getValue();
        }
        return value;
    }

    public static String toString(int value) {
        String result = new String("");
        Set<FileAccessFlags> flags = getFlags(value);

        for (FileAccessFlags flag: flags) {
            result += flag.toString()+ " | ";
        }

        return result;
    }


}
