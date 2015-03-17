package com.github.sherter.jdokan;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.common.annotations.VisibleForTesting;

final class Loader {

  private Loader() {}

  @VisibleForTesting
  static void copyResource(ClassLoader loader, String resource, Path dest) throws IOException {
    InputStream in = loader.getResourceAsStream(resource);
    Files.copy(in, dest);
  }

  static void loadResourceAsSharedLibrary(ClassLoader loader, String resource) {
    requireNonNull(loader).getResource(requireNonNull(resource));
  }

  static boolean isLoadedFromJar(Class<?> clazz) {
    requireNonNull(clazz);
    return clazz.getResource(clazz.getSimpleName() + ".class").toString().startsWith("jar");
  }
}
