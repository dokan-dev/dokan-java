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

package com.github.dokandev.dokanjava.util;

import java.util.Set;

public enum ShareMode implements BitMask {

  FILE_SHARE_DELETE(0x00000004), FILE_SHARE_READ(0x00000001), FILE_SHARE_WRITE(0x00000002);

  private final int mask;

  private ShareMode(int mask) {
    this.mask = mask;
  }
  
  @Override
  public int mask() {
    return mask;
  }

  /**
   * Extracts all matching {@link ShareMode}s from the given integer representation.
   * 
   * @see https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx
   */
  public static Set<ShareMode> fromInt(int shareMode) {
    return BitMask.fromInt(ShareMode.class, shareMode);
  }

  /**
   * Generates a integer representation from the given {@link ShareMode}s.
   * 
   * @see https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx
   */
  public static int toInt(ShareMode... modes) {
    return BitMask.toInt(modes);
  }
}
