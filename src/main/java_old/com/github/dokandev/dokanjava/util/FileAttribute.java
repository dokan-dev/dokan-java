/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/ 
 *               2009 Caleido AG http://www.wuala.com/
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

package com.github.dokandev.dokanjava.util;

import java.util.Set;

public enum FileAttribute implements BitMask {

  FILE_ATTRIBUTE_ARCHIVE(0x20),
  FILE_ATTRIBUTE_COMPRESSED(0x800),
  FILE_ATTRIBUTE_DEVICE(0x40),
  FILE_ATTRIBUTE_DIRECTORY(0x10),
  FILE_ATTRIBUTE_ENCRYPTED(0x4000),
  FILE_ATTRIBUTE_HIDDEN(0x2),
  FILE_ATTRIBUTE_INTEGRITY_STREAM(0x8000),
  FILE_ATTRIBUTE_NORMAL(0x80),
  FILE_ATTRIBUTE_NOT_CONTENT_INDEXED(0x2000),
  FILE_ATTRIBUTE_NO_SCRUB_DATA(0x20000),
  FILE_ATTRIBUTE_OFFLINE(0x1000),
  FILE_ATTRIBUTE_READONLY(0x1),
  FILE_ATTRIBUTE_REPARSE_POINT(0x400),
  FILE_ATTRIBUTE_SPARSE_FILE(0x200),
  FILE_ATTRIBUTE_SYSTEM(0x4),
  FILE_ATTRIBUTE_TEMPORARY(0x100),
  FILE_ATTRIBUTE_VIRTUAL(0x10000);

  private final int value;

  private FileAttribute(int value) {
    this.value = value;
  }

  @Override
  public int mask() {
    return value;
  }
  
  public static Set<FileAttribute> fromInt(int flagsAndAttributes) {
    return BitMask.fromInt(FileAttribute.class, flagsAndAttributes);
  }

  public static int toInt(FileAttribute... attributes) {
    return BitMask.toInt(attributes);
  }
}
