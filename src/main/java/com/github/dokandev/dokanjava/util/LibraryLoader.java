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

public final class LibraryLoader {

  private static final Logger log = LoggerFactory.getLogger(LibraryLoader.class);

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
          continue;
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
        String[] splits = filename.split("\\.", 2);
        Path temp = Files.createTempFile(splits[0], splits.length > 1 ? splits[1] : ".tmp");
        deleteExistingTempFiles(temp.getParent(), splits[0], splits.length > 1 ? splits[1] : ".tmp");
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
  static void deleteExistingTempFiles(Path tempDir, String prefix, String suffix) {
    try {
      Files
          .list(tempDir)
          .filter(
              f -> f.getFileName().toString().startsWith(prefix)
                  && f.getFileName().toString().endsWith(suffix))
          .forEach(
              f -> {
                try {
                  Files.delete(f);
                } catch (IOException e) {
                  log.info("Couldn't delete temporary file {}, which was most"
                      + " likely created on previous runs of this program."
                      + " You may wan't to try to delete it manually.", f);
                }
              });
    } catch (IOException e) {
      // TODO is ignoring good enough?
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
