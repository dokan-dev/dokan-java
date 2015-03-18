/*
 * JDokan : Java library for Dokan
 * 
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
 * 
 * http://decas-dev.net/en
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

package com.github.sherter.jdokan;

import com.github.sherter.jdokan.util.LibraryLoader;

public final class Dokan {

  static {
    LibraryLoader.load("jdokan");
  }

  private Dokan() {}

  public static native int mount(DokanOptions options, DokanOperations operations);

  public static native boolean unmount(char driveLetter);

  public static native boolean removeMountPoint(String mountPoint);

  public static native boolean isNameInExpression(String expression, String name, boolean ignoreCase);

  public static native int version();

  public static native int driverVersion();

}
