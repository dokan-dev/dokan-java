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

public enum FileFlag implements BitMask {

  FILE_FLAG_BACKUP_SEMANTICS(0x02000000),
  FILE_FLAG_DELETE_ON_CLOSE(0x04000000),
  FILE_FLAG_NO_BUFFERING(0x20000000),
  FILE_FLAG_OPEN_NO_RECALL(0x00100000),
  FILE_FLAG_OPEN_REPARSE_POINT(0x00200000),
  FILE_FLAG_OVERLAPPED(0x40000000),
  FILE_FLAG_POSIX_SEMANTICS(0x01000000),
  FILE_FLAG_RANDOM_ACCESS(0x10000000),
  FILE_FLAG_SESSION_AWARE(0x00800000),
  FILE_FLAG_SEQUENTIAL_SCAN(0x08000000),
  FILE_FLAG_WRITE_THROUGH(0x80000000);

  private final int mask;

  private FileFlag(int mask) {
    this.mask = mask;
  }

  @Override
  public int mask() {
    return mask;
  }

  public static Set<FileFlag> fromInt(int flagsAndAttributes) {
    return BitMask.fromInt(FileFlag.class, flagsAndAttributes);
  }

  public static int toInt(FileFlag... flags) {
    return BitMask.toInt(flags);
  }
}
