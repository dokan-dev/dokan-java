/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 
 *   Saine Imad
 *   2015 Simon Herter <sim.herter@gmail.com>
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
package com.github.dokandev.dokanjava;


public enum DokanOption {
  DEBUG(1), 
  STDERR(1 << 1), 
  ALT_STREAM(1 << 2), 
  KEEP_ALIVE(1 << 3), 
  NETWORK(1 << 4), 
  REMOVABLE(1 << 5);

  private final int mask;

  private DokanOption(int mask) {
    this.mask = mask;
  }

  /**
   * Translates a set of options into a bit field.
   */
  public static int bitFieldFrom(DokanOption... options) {
    int bitField = 0;
    for (DokanOption o : options) {
      bitField |= o.mask;
    }
    return bitField;
  }
}
