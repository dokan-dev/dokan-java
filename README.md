Dokany-Java
======

##Introduction
Dokany-Java is a Java wrapper for [Dokany 1.x release](https://github.com/dokan-dev/dokany/releases) and above using [JNA](https://github.com/java-native-access/jna).

For an example on how to use this library, see the examples package [com.dokany.java.examples](src//main/java/com/dokany/java/examples/).

## Download
Versions are available on Bintray [repository](https://bintray.com/jhult/maven/com.dokany%3Adokany-java).

Add the following to your `build.gradle`:

```groovy
repositories {
  maven {
    url 'https://dl.bintray.com/jhult/maven/'
  }
}
dependencies {
  compile 'com.dokany:dokany-java:0.1'
}
```