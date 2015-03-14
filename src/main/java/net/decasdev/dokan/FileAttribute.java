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

package net.decasdev.dokan;

import java.util.EnumSet;
import java.util.Set;

public class FileAttribute {
		public static final int ATTRIBUTE_MASK= 0x0000FFFF;
	
    public enum FileAttributeFlags {
         FILE_ATTRIBUTE_ARCHIVE(0x00000020),
         FILE_ATTRIBUTE_COMPRESSED(0x00000800),
         FILE_ATTRIBUTE_DIRECTORY(0x00000010),
         FILE_ATTRIBUTE_ENCRYPTED(0x00000040),
         FILE_ATTRIBUTE_HIDDEN(0x00000002),
         FILE_ATTRIBUTE_NORMAL(0x00000080),
         FILE_ATTRIBUTE_OFFLINE(0x00001000),
         FILE_ATTRIBUTE_READONLY(0x00000001),
         FILE_ATTRIBUTE_REPARSE_POINT(0x00000400),
         FILE_ATTRIBUTE_SPARSE_FILE(0x00000200),
         FILE_ATTRIBUTE_SYSTEM(0x00000004),
         FILE_ATTRIBUTE_TEMPORARY(0x00000100),
         FILE_ATTRIBUTE_NOT_CONTENT_INDEXED(0x00002000),
         FILE_ATTRIBUTE_VIRTUAL(0x00010000);

        private int value;

        FileAttributeFlags(int value) {
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
    public static EnumSet<FileAttributeFlags> getFlags(int value)
    {
        EnumSet<FileAttributeFlags> flags = EnumSet.noneOf(FileAttributeFlags.class);

        for (FileAttributeFlags flag: FileAttributeFlags.values()) {
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
    public static long getStatusValue(Set<FileAttributeFlags> flags)
    {
        long value=0;
        for (FileAttributeFlags flag: flags) {
            value |= flag.getValue();
        }
        return value;
    }

    public static String toString(int value) {
        String result = new String("");
        Set<FileAttributeFlags> flags = getFlags(value);

        for (FileAttributeFlags flag: flags) {
            result += flag.toString()+ " | ";
        }

        return result;
    }

}
