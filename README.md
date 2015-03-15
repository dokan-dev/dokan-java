Jdokan [![Build status](https://ci.appveyor.com/api/projects/status/bbc77ypvx96ijqif?svg=true)](https://ci.appveyor.com/project/sherter/jdokan-nu5cd)
======

Jdokan is a Java wrapper for Dokan 0.6.0 and above using JNI.
The original version is no longer maintained. I made this fork 
in order to clean the library up and improve the build process (with gradle).

For example how this library can be used, see [opendedup](http://code.google.com/p/opendedup/source/browse/trunk/src/org/opendedup/sdfs/windows/fs/WinSDFS.java)

## Building
This project uses [gradle](http://gradle.org/) as build tool.

1. Make sure `'JAVA_HOME` env variable is pointing to your JDK installation
2. Download and install the latest [Dokany](https://github.com/Maxhy/dokany/releases) release
3. Point `DOKAN_HOME` env variable to the installation directory (usually `C:\Program Files (x86)\Dokan\DokanLibrary`)

Now you should be able to use gradle as usually.
