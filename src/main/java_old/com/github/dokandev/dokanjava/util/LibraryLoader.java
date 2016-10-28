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

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;

public final class LibraryLoader {

  private static final Logger log = LoggerFactory.getLogger(LibraryLoader.class);

  private LibraryLoader() {}

  /**
   * Loads the native library specified by the {@code libname} argument. The {@code libname}
   * argument must not contain any platform specific prefix, file extension or path. Searches for
   * the native library in the following locations (in this order):
   * <ul>
   * <li>{@code java.library.path}</li>
   * <li>{@code java.class.path}
   * <li>{@code .jar} file from which this {@link LibraryLoader} was loaded.</li>
   * </ul>
   * Returns as soon as a match is found. In case the native library is found inside a {@code .jar}
   * file, it is extracted to a temp file and loaded from there. There is no guarantee that the temp
   * file is deleted after jvm shutdown.
   * 
   * @param libname name of the native library without platform specific prefix, file extension or
   *        path.
   * 
   * @throws UnsatisfiedLinkError in case the native library could not be found
   */
  public static void load(String libname) {
    try {
      log.debug("loading native library '{}' from java.library.path ...", libname);
      System.loadLibrary(libname);
      log.debug("Successfully loaded library '{}' from java.library.path", libname);
      return;
    } catch (UnsatisfiedLinkError e) {
      // not in java.library.path
    }
    boolean isLoaded = loadFromClasspath(libname);
    if (!isLoaded && isLoadedFromJar(LibraryLoader.class)) {
      isLoaded = loadFromJar(libname);
    }
    if (!isLoaded) {
      throw new UnsatisfiedLinkError(
          "native library is missing or was compiled for wrong architecture");
    }
  }

  private static boolean loadFromClasspath(String libname) {
    log.debug("loading native library '{}' from java.class.path ...", libname);
    String filename = System.mapLibraryName(libname);
    List<Path> classPathDirs =
        Arrays.stream(System.getProperty("java.class.path").split(File.pathSeparator))
            .map(p -> Paths.get(p)).filter(p -> Files.isDirectory(p)).collect(Collectors.toList());
    for (Path dir : classPathDirs) {
      Path libPath = dir.resolve(filename);
      if (Files.exists(libPath)) {
        try {
          System.load(libPath.toString());
          log.debug("Successfully loaded library '{}' from '{}'", libname, libPath.normalize());
          return true;
        } catch (UnsatisfiedLinkError e) {
          continue;
        }
      }
    }
    return false;
  }

  private static boolean loadFromJar(String libname) {
    log.debug("loading native library '{}' from '{}'", libname, jarFileName());
    String filename = System.mapLibraryName(libname);
    if (ClassLoader.getSystemClassLoader().getResource(filename) == null) {
      log.debug("{} was not found in {}", filename, jarFileName());
      return false;
    } else {
      try {
        String[] splits = filename.split("\\.", 2);
        Path temp = Files.createTempFile(splits[0], splits.length > 1 ? "." + splits[1] : ".dll");
        log.debug("extracting '{}' from '{}' to '{}'", filename, jarFileName(), temp);
        temp.toFile().deleteOnExit();
        copyResource(ClassLoader.getSystemClassLoader(), filename, temp,
            StandardCopyOption.REPLACE_EXISTING);
        System.load(temp.toString());
        return true;
      } catch (IOException e) {
        throw Throwables.propagate(e);
      }
    }
  }

  private static String jarFileName() {
    return new File(LibraryLoader.class.getProtectionDomain().getCodeSource().getLocation()
        .getPath()).getName();
  }

  @VisibleForTesting
  static void copyResource(ClassLoader loader, String resource, Path dest, CopyOption... options)
      throws IOException {
    InputStream in = loader.getResourceAsStream(resource);
    Files.copy(in, dest, options);
  }

  @VisibleForTesting
  static boolean isLoadedFromJar(Class<?> clazz) {
    requireNonNull(clazz);
    return clazz.getResource(clazz.getSimpleName() + ".class").toString().startsWith("jar");
  }

}
