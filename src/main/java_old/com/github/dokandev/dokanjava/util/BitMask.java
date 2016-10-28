/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2015 Simon Herter <sim.herter@gmail.com>
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

import java.util.EnumSet;
import java.util.Set;

interface BitMask {

  int mask();

  static <E extends Enum<E> & BitMask> Set<E> fromInt(Class<E> clazz, int bitMask) {
    EnumSet<E> set = EnumSet.noneOf(clazz);
    for (E e : clazz.getEnumConstants()) {
      if ((e.mask() & bitMask) == e.mask()) {
        set.add(e);
      }
    }
    return set;
  }
  
  static <E extends Enum<E> & BitMask> int toInt(E[] elements) {
    int mask = 0;
    for (E e : elements) {
      mask |= e.mask();
    }
    return mask;
  }
}
