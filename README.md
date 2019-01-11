Dokan-Java
======

Dokan-Java is a Java wrapper for [Dokany 1.x release](https://github.com/dokan-dev/dokany/releases) and above.
## Important Notice
This project has switched to a new architecture. The _old_ dokan-java can be found under the deprecated branch and will be deleted after 6 months.

## Introduction
Dokany is a device driver providing an interface to develop and run your own filesystem on Windows (similar to FUSE). But using the library directly you need to write your filesystem code in C... but fortunately there is an escape from the pointer hell: dokan-java. Using this project you are able to write your filesystem in Java!

## Runtime Dependencies
- [Java JRE 11](https://jdk.java.net/11/)

All dependencies can be seen in the dependecies section of [the build file](build.gradle).

- [JNA](https://github.com/java-native-access/jna) - provides access to [native Dokany functions](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html)
- [Commons IO](https://commons.apache.org/proper/commons-io/)
- [SLF4J](https://www.slf4j.org/)
- [Guava](https://github.com/google/guava)
- [JUnit 5](https://junit.org/junit5/)
	
## How to Build
Prerequisite: JDK 11

Building Jar :
 1. Open Terminal
 1. Navigate to the root folder of the project 
 1. Execute `./gradlew.bat jar`

To publish to the local maven repository, execute as the third step `./gradlew.bat publishToMavenLocal`

## Download
The first stable release can be found here: https://bintray.com/infeo/maven/dokan-java

A publication to JCenter and MavenCentral is in progress.

## Development Examples
Example user filesystems using this library, see the examples package [com.dokan.java.examples](https://github.com/dokan-dev/dokan-java/tree/develop/src/main/java/com/dokan/java/examples).
