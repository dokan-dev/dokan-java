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

public enum CreationDisposition {
  CREATE_NEW(1), CREATE_ALWAYS(2), OPEN_EXISTING(3), OPEN_ALWAYS(4), TRUNCATE_EXISTING(5);

  private final int value;

  private CreationDisposition(int value) {
    this.value = value;
  }

  public static CreationDisposition fromInt(int value) {
    for (CreationDisposition current : values()) {
      if (current.value == value) {
        return current;
      }
    }
    throw new IllegalArgumentException();
  }


}
