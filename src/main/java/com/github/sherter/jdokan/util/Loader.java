package com.github.sherter.jdokan.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.google.common.annotations.VisibleForTesting;

public final class Loader {

  private Loader() {}

  /**
   * Uses the given {@link ClassLoader} to find and copy the content of the given resource to a
   * newly created temporary file. The created temporary file will be automatically deleted on jvm
   * shutdown.
   * 
   * @param classLoader
   * @param resource a path to a resource as defined in {@link ClassLoader#getResource(String)}
   * @throws IOException if temporary file could not be created and/or written
   */
  public static Path copy(ClassLoader classLoader, String resource) throws IOException {
    Path temp = Files.createTempFile("jdokan_", null);
    copyResource(classLoader, resource, temp);
    deleteOnExit(temp);
    return temp;
  }

  @VisibleForTesting
  static void copyResource(ClassLoader loader, String resource, Path dest) throws IOException {
    InputStream in = loader.getResourceAsStream(resource);
    Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
  }

  private static void deleteOnExit(Path p) {
    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> {
          try {
            Files.delete(p);
          } catch (Exception e) {
            System.err.println("Could not delete temp file" + p
                + ". You may wan't to delete it manually!");
          }
        }));
  }


  /** Returns true if the given {@code clazz} was loaded from within a JAR file */
  public static boolean isLoadedFromJar(Class<?> clazz) {
    requireNonNull(clazz);
    return clazz.getResource(clazz.getSimpleName() + ".class").toString().startsWith("jar");
  }

}
