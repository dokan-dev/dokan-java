Dokan-Java [![Build status](https://ci.appveyor.com/api/projects/status/52nkbt94kirwaxsc/branch/master?svg=true)](https://ci.appveyor.com/project/sherter/jdokan/branch/master)
======

Dokan-Java is a Java wrapper for Dokan 0.6.0 and above using JNI.
The original version is no longer maintained. I made this fork 
in order to clean the library up and improve the build process (with gradle).

For an example on how to use this library see 
[opendedup](http://code.google.com/p/opendedup/source/browse/trunk/src/org/opendedup/sdfs/windows/fs/WinSDFS.java).

## Download
Snapshot versions are available on sonatype snapshot [repository](https://oss.sonatype.org/content/repositories/snapshots/com/github/dokan-dev/dokan-java/dokan-java/).

Add the following to your `build.gradle`:

```groovy
repositories {
  maven {
    url 'https://oss.sonatype.org/content/repositories/snapshots/'
  }
}
dependencies {
  compile 'com.github.dokan-dev.dokan-java:dokan-java:0.1-SNAPSHOT'
}
```

## Building
This project uses [gradle](http://gradle.org/) as build tool.

1. Make sure `JAVA_HOME` env variable is pointing to your JDK installation
2. Download and install the latest [Dokany](https://github.com/Maxhy/dokany/releases) release
3. Point `DOKAN_HOME` env variable to the installation directory 
(e.g. `C:\Program Files (x86)\Dokan\DokanLibrary`)

Now you should be able to use gradle as usually.

