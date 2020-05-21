Dokan-Java
======

Dokan-Java is a Java wrapper for [Dokany 1.3.0 releases](https://github.com/dokan-dev/dokany/releases) and above.

## Introduction
[Dokany](https://github.com/dokan-dev/dokany) is a device driver providing an interface to develop and run your own filesystem on Windows (similar to [FUSE](https://github.com/libfuse/libfuse)).
It is natively  written in C/C++, this library provides via [JNA](https://github.com/java-native-access/jna) a Java interface to its API with additional convenience methods.
Using this project you are able to implement your own filesystem on Windows... in Java!

## Runtime Dependencies
dokan-java requires [Java JRE 11](https://jdk.java.net/11/).

All dependencies can be seen in the dependecies section of [the build file](build.gradle).
The following are the primary dependencies:

- [JNA](https://github.com/java-native-access/jna) - provides access to [native Dokany functions](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html)
- [JUnit 5](https://junit.org/junit5/)

## How to Use
There are 2 ways to directly use dokan-java:
1. Use a pre-built version
2. Build it yourself

### Pre-Built Version
Release can be found on [bintray](https://bintray.com/infeo/maven/dokan-java).

The project publishs to jcenter for easy integration with maven or gradle projects.

#### Maven

In order to include dokan-java, add jcenter as an additional repository in your `pom.xml`
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```
and add the project as a dependency:
```xml
<dependency>
    <groupId>dev.dokan</groupId>
    <artifactId>dokan-java</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### Gradle

In order to use dokan-java, add jcenter to your `build.gradle`
```groovy
repositories {
    jcenter()
}
```
and add the project as a dependency:
```groovy
dependencies {
    implementation (group:'dev.dokan', name:'dokan-java', version:'1.1.1')
}
```
	
### Build Instructions
Prerequisite: [JDK 11](https://jdk.java.net/11/)

Building a jar using [Gradle](https://gradle.org/):
 1. Open Terminal
 2. Navigate to the root folder of the project 
 3. Execute `./gradlew.bat jar`

To publish to your local Maven repository, execute as the third step `./gradlew.bat publishToMavenLocal`

## Examples
An example user filesystems using this library can be found in the examples package [dev.dokan.dokan_java.examples](https://github.com/dokan-dev/dokan-java/tree/develop/src/main/java/dev/dokan/dokan_java/examples).

## Versioning
Dokan-Java uses it's own versioning system and therefore doesn't match the versions of Dokany.
For Dokan-Java to work you need to have a suitable version of Dokany installed on your machine. The following table helps you choose the correct Dokany-version.

| Dokan-Java Version | Minimum Version of Dokany |
|--------------------|---------------------------|
| 1.0.0 - 1.1.X      | 1.2.0.1000                |
| 1.2.0+             | 1.3.0.1000                |

## Contributing
You're encouraged to contribute.
 Fork the code and then submit a pull request.

## License
This library is licensed under [GNU LGPLv3](LICENSE).
