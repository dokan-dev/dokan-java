Dokany-Java
======

# Important Notice
This project will switch to a new architecture with a new interface. Therefore the current main-branch will be moved to a deprecated branch and then deleted after 6 months. The information provided below is valid for this deprecated adapter. 

The migration will take some time but if you are already interested int the "new" java-wrapper, have a look [here](https://github.com/infeo/dokan-java).

## Introduction
Dokany-Java is a Java wrapper for [Dokany 1.x release](https://github.com/dokan-dev/dokany/releases) and above.

## Runtime Dependencies
- [Java JRE 1.8](https://java.com/en/download/manual.jsp)

All dependencies can be seen [here](build.gradle).

- [JNA](https://github.com/java-native-access/jna) - provides access to [native Dokany functions](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html)
- [Project Lombok](https://projectlombok.org/) - reduces boilerplate code
	- [Eclipse installation instructions](https://stackoverflow.com/questions/3418865/cannot-make-project-lombok-work-on-eclipse-helios/)
- [Commons IO](https://commons.apache.org/proper/commons-io/)
- [SLF4J](https://www.slf4j.org/)
- [Logback](https://logback.qos.ch/)
	
## How to Build
Requires [Java JDK 1.8](http://www.azul.com/downloads/zulu/)

<!---### Gradle
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
-->

## Development Examples
For an example on how to develop using this library, see the examples package [com.dokany.java.examples](src//main/java/com/dokany/java/examples/).
