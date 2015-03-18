package com.github.sherter.jdokan.util;

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

import com.google.common.annotations.VisibleForTesting;

public final class LibraryLoader {

  private LibraryLoader() {}

  public static void load(String libname) {
    try {
      System.loadLibrary(libname);
      return;
    } catch (UnsatisfiedLinkError e) {
      // not in java.library.path
    }
    boolean loaded = false;
    if (isLoadedFromJar(LibraryLoader.class)) {
      loaded = loadFromJar(libname);
    } else {
      loaded = loadFromClasspath(libname);
    }
    if (!loaded) {
      throw new UnsatisfiedLinkError(
          "native library is missing or was compiled for wrong architecture");
    }
  }

  private static boolean loadFromClasspath(String libname) {
    String filename = System.mapLibraryName(libname);
    List<Path> classPathDirs =
        Arrays.stream(System.getProperty("java.class.path").split(File.pathSeparator))
            .map(p -> Paths.get(p)).filter(p -> Files.isDirectory(p)).collect(Collectors.toList());
    for (Path dir : classPathDirs) {
      Path libPath = dir.resolve(filename);
      if (Files.exists(libPath)) {
        try {
          System.load(libPath.toString());
          return true;
        } catch (UnsatisfiedLinkError e) {
          // try next
        }
      }
    }
    return false;
  }

  private static boolean loadFromJar(String libname) {
    String filename = System.mapLibraryName(libname);
    if (ClassLoader.getSystemClassLoader().getResource(filename) == null) {
      return false;
    } else {
      try {
        Path temp = Files.createTempFile("jdokan_", ".dll");
        temp.toFile().deleteOnExit();
        copyResource(ClassLoader.getSystemClassLoader(), filename, temp,
            StandardCopyOption.REPLACE_EXISTING);
        System.load(temp.toString());
        return true;
      } catch (IOException | UnsatisfiedLinkError e) {
        return false;
      }
    }
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
