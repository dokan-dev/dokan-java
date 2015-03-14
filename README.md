jdokan
======

jdokan is a Java wrapper for Dokan 6.0 and above.

Take a look at opendedup source for detail on how this works.

http://code.google.com/p/opendedup/source/browse/trunk/src/org/opendedup/sdfs/windows/fs/WinSDFS.java

# Building
This project uses [gradle](http://gradle.org/) as build tool.

1. Make sure 'JAVA_HOME' env variable is pointing to your JDK installation
2. Download and install the latest [Dokany](https://github.com/Maxhy/dokany/releases) release
3. Point 'DOKAN_HOME' env variable to the installation directory (usually C:\Program Files (x86)\Dokan\DokanLibrary)

Now you should be able to use gradle as you know it.
