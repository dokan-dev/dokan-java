Dokany-Java
======

# Important Notice
This project will switch to a new architecture with a new interface. Therefore the current main-branch will be moved to a deprecated branch and then deleted after 6 months. The information provided below is valid for this deprecated adapter. 

The migration will take some time but if you are already interested int the "new" java-wrapper, have a look [here](https://github.com/infeo/dokan-java).

## Introduction
Dokany-Java is a Java wrapper for [Dokany 1.x release](https://github.com/dokan-dev/dokany/releases) and above.

## Runtime Dependencies
- [Java JRE 11](https://jdk.java.net/11/)

All dependencies can be seen [here](build.gradle).

- [JNA](https://github.com/java-native-access/jna) - provides access to [native Dokany functions](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html)
- [Commons IO](https://commons.apache.org/proper/commons-io/)
- [SLF4J](https://www.slf4j.org/)
- [Guava](https://github.com/google/guava)
- [JUnit 5](https://junit.org/junit5/)
	
## How to Build
TODO

## Development Examples
For an example on how to develop using this library, see the examples package [com.dokan.java.examples](https://github.com/dokan-dev/dokan-java/tree/develop/src/main/java/com/dokan/java/examples).
