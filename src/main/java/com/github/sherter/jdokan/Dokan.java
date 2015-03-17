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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.sherter.jdokan.util.Loader;

public final class Dokan {

  private static final String libname = "jdokan";

  private Dokan() {}

  static {
    boolean loaded = loadFromJavaLibraryPath();
    if (!loaded) {
      if (Loader.isLoadedFromJar(Dokan.class)) {
        loaded = loadFromJar();
      } else {
        loaded = loadFromClasspath();
      }
    }
    if (!loaded) {
      throw new UnsatisfiedLinkError("native shared library is missing");
    }
  }

  private static boolean loadFromJavaLibraryPath() {
    try {
      System.loadLibrary(libname);
      return true;
    } catch (UnsatisfiedLinkError e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean loadFromJar() {
    String resourcePath = libname + ".dll";
    if (Dokan.class.getClassLoader().getResource(resourcePath) == null) {
      return false;
    } else {
      try {
        Path tempDll = Loader.copy(Dokan.class.getClassLoader(), libname + ".dll");
        System.load(tempDll.toString());
        return true;
      } catch (IOException e) {
        return false;
      } catch (UnsatisfiedLinkError e) {
        System.out.println(e.getMessage());
        return false;
      }
    }
  }

  private static boolean loadFromClasspath() {
    String[] paths = System.getProperty("java.class.path").split(";");
    List<String> dirs =
        Arrays.stream(paths).filter(path -> !path.endsWith("jar")).collect(Collectors.toList());
    for (String dir : dirs) {
      try {
        System.load(dir + "\\" + libname + ".dll");
        return true;
      } catch (UnsatisfiedLinkError e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
    return false;
  }

  public static native int mount(DokanOptions options, DokanOperations operations);

  public static native boolean unmount(char driveLetter);

  public static native boolean removeMountPoint(String mountPoint);

  public static native boolean isNameInExpression(String expression, String name, boolean ignoreCase);

  public static native int version();

  public static native int driverVersion();

}
