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

public enum AccessMask implements BitMask {

  GENERIC_ALL(0x10), GENERIC_EXECUTE(0x20), GENERIC_WRITE(0x40), GENERIC_READ(0x80);

  private final int mask;

  private AccessMask(int value) {
    this.mask = value;
  }

  @Override
  public int mask() {
    return mask;
  }
  
  /**
   * Extracts all matching {@link AccessMask}s from the given integer representation.
   * 
   * @see https://msdn.microsoft.com/en-us/library/windows/desktop/aa374892(v=vs.85).aspx
   */
  public static Set<AccessMask> fromInt(int accessMask) {
    return BitMask.fromInt(AccessMask.class, accessMask);
  }


  /**
   * Generates a integer representation from the given {@link AccessMask}s.
   * 
   * @see https://msdn.microsoft.com/en-us/library/windows/desktop/aa374892(v=vs.85).aspx
   */
  public static int toInt(AccessMask... masks) {
    return BitMask.toInt(masks);
  }
}
