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

package com.github.sherter.jdokan;

import java.util.EnumSet;
import java.util.Set;

public class FileSecurity {
    public enum FileSecurityFlags{
         SECURITY_ANONYMOUS(0x00000000),
         SECURITY_CONTEXT_TRACKING(0x00040000),
         SECURITY_DELEGATION(0x00030000),
         SECURITY_EFFECTIVE_ONLY(0x00080000),
         SECURITY_IDENTIFICATION(0x00010000),
         SECURITY_IMPERSONATION(0x00020000);

        private int value;

        FileSecurityFlags(int value) {
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
    public static EnumSet<FileSecurityFlags> getFlags(int value)
    {
        EnumSet<FileSecurityFlags> flags = EnumSet.noneOf(FileSecurityFlags.class);

        for (FileSecurityFlags flag: FileSecurityFlags.values()) {
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
    public static long getStatusValue(Set<FileSecurityFlags> flags)
    {
        long value=0;
        for (FileSecurityFlags flag: flags) {
            value |= flag.getValue();
        }
        return value;
    }

    public static String toString(int value) {
        String result = new String("");
        Set<FileSecurityFlags> flags = getFlags(value);

        for (FileSecurityFlags flag: flags) {
            result += flag.toString()+ " | ";
        }

        return result;
    }
}
