package com.github.dokandev.dokanjava.util;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.github.dokandev.dokanjava.util.LibraryLoader;
import com.google.common.io.ByteStreams;
import com.google.common.jimfs.Jimfs;

public class LibraryLoaderTest {

  @Test
  public void copyResourceDestinationFileHasSameContentAsResource() throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    String resource = getClass().getName().replaceAll("\\.", "/") + ".class";
    Path dest = Jimfs.newFileSystem().getPath("tmp");
    LibraryLoader.copyResource(classLoader, resource, dest);
    byte[] sourceBytes = ByteStreams.toByteArray(classLoader.getResourceAsStream(resource));
    byte[] destBytes = Files.readAllBytes(dest);
    assertThat(destBytes).isEqualTo(sourceBytes);
  }

  @Test
  public void isNotLoadedFromJar() {
    assertThat(LibraryLoader.isLoadedFromJar(getClass())).isFalse();
  }


  @SuppressWarnings("resource")
  @Test
  public void isLoadedFromJar() throws IOException, ClassNotFoundException {
    URL[] jarFiles = {new URL("jar:file:./src/test/resources/loader-test.jar!/")};
    URLClassLoader classLoader = new URLClassLoader(jarFiles);
    Class<?> clazz = classLoader.loadClass("Loader");
    assertThat(LibraryLoader.isLoadedFromJar(clazz)).isTrue();
  }
}
