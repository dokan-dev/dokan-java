Dokan-Java
======
## Introduction
Dokan-Java is a Java wrapper for [Dokany 1.x releases](https://github.com/dokan-dev/dokany/releases) and above. Using this project you are able to implement your own filesystem in Java!

[Dokany](https://github.com/dokan-dev/dokany) is a device driver providing an interface to develop and run your own filesystem on Windows (similar to [FUSE](https://github.com/libfuse/libfuse)). But using the library directly you need to write your filesystem code in C... but fortunately there is an escape from the pointer hell: dokan-java.

## Important Notice
This project has switched to a new architecture. The _old_ version of dokan-java can be found under [the deprecated branch](../../tree/deprecated) and will be deleted in June 2019.

## Runtime Dependencies
- [Java JRE 11](https://jdk.java.net/11/)

All dependencies can be seen in the dependecies section of [the build file](build.gradle). The following are the primary dependencies:

- [JNA](https://github.com/java-native-access/jna) - provides access to [native Dokany functions](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html)
- [Commons IO](https://commons.apache.org/proper/commons-io/)
- [SLF4J](https://www.slf4j.org/)
- [Guava](https://github.com/google/guava)
- [JUnit 5](https://junit.org/junit5/)

## How to Use
There are 2 ways to directly use dokan-java:
1. Use a pre-built version
2. Build it yourself

### Pre-Built Version
The first stable release can be found here: https://bintray.com/infeo/maven/dokan-java

A publication to [JCenter](https://bintray.com/bintray/jcenter) and [MavenCentral](https://repo.maven.apache.org/maven2/) is in progress.
	
### Build Instructions
Prerequisite: [JDK 11](https://jdk.java.net/11/)

Building a jar using [Gradle](https://gradle.org/):
 1. Open Terminal
 2. Navigate to the root folder of the project 
 3. Execute `./gradlew.bat jar`

To publish to a local Maven repository, execute as the third step `./gradlew.bat publishToMavenLocal`

## Examples
Example user filesystems using this library, see the examples package [dev.dokan.dokan_java.examples](https://github.com/dokan-dev/dokan-java/tree/develop/src/main/java/dev/dokan/dokan_java/examples).

## Contributing
You're encouraged to contribute. Fork the code and then submit a pull request.

## License
This library is licensed under [GNU LGPLv3](LICENSE).
