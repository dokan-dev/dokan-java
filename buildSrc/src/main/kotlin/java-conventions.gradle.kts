// Define Java conventions for this organization.

plugins {
    java
    checkstyle
}

// Projects should use Maven Central for external dependencies
repositories {
    mavenCentral()
}

/*
// Use the Checkstyle rules provided by the convention plugin
// Do not allow any warnings
checkstyle {
    config = resources.text.fromString(com.example.CheckstyleUtil.getCheckstyleConfig("/checkstyle.xml"))
    maxWarnings = 0
}
 */

// Enable deprecation messages when compiling Java code
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}
