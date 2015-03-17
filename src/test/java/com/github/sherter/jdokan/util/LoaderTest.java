package com.github.sherter.jdokan.util;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.google.common.io.ByteStreams;
import com.google.common.jimfs.Jimfs;

public class LoaderTest {

  @Test(expected = UnsatisfiedLinkError.class)
  public void loadNonExistentLibrary() throws Exception {
//    Loader.loadLibrary("i_do_not_exist");
  }

  @Test
  public void loadExistentx86LibraryFromOutsideJar() throws Exception {
    if (System.getProperty("os.arch").equals("x86")) {
//      Loader.loadLibrary("jdokan_x86");
    }
  }

  @Test
  public void loadExistentamd64LibraryFromOutsideJar() throws Exception {
    if (System.getProperty("os.arch").equals("amd64")) {
 //     Loader.loadLibrary("jdokan_x64");
    }
  }

  @Test
  public void destinationFileHasSameContentAsResource() throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    String resource = getClass().getName().replaceAll("\\.", "/") + ".class";
    Path dest = Jimfs.newFileSystem().getPath("tmp");
    Loader.copyResource(classLoader, resource, dest);
    byte[] sourceBytes = ByteStreams.toByteArray(classLoader.getResourceAsStream(resource));
    byte[] destBytes = Files.readAllBytes(dest);
    assertThat(destBytes).isEqualTo(sourceBytes);
  }

  @Test
  public void isNotLoadedFromJar() {
    assertThat(Loader.isLoadedFromJar(getClass())).isFalse();
  }

  @SuppressWarnings("resource")
  @Test
  public void isLoadedFromJar() throws IOException, ClassNotFoundException {
    URL[] jarFiles = {new URL("jar:file:./src/test/resources/loader-test.jar!/")};
    URLClassLoader classLoader = new URLClassLoader(jarFiles);
    Class<?> clazz = classLoader.loadClass("Loader");
    assertThat(Loader.isLoadedFromJar(clazz)).isTrue();
  }

}
