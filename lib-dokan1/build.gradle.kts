version = rootProject.version
group= rootProject.group
description="A JNA-based Java wrapper for Dokany 1.x"

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.mockito:mockito-core:4.4.0")

    api("net.java.dev.jna:jna-jpms:5.11.0")
    api("net.java.dev.jna:jna-platform-jpms:5.11.0")

}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

/**
 * Jar packaging
 */
tasks.jar {
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Specification-Title" to project.name,
            "Specification-Version" to project.semVerString(),
        ))
    }
}

publishing {
    publications {
        create<MavenPublication>("dokan-java-core") {
            from(components["java"])
            pom {
                description.set(project.description)
                url.set(rootProject.extra.get("url") as String);
                licenses {
                    license {
                        name.set(rootProject.extra.get("license") as String)
                        url.set(rootProject.extra.get("licenseUrl") as String)
                    }
                }
                issueManagement {
                    url.set(rootProject.extra.get("issueManagementUrl") as String)
                    system.set(rootProject.extra.get("issueManagementSystem") as String)
                }
                scm {
                    url.set(rootProject.extra.get("scmUrl") as String)
                }
                organization {
                    name.set(rootProject.extra.get("orgName") as String)
                    url.set(rootProject.extra.get("orgUrl") as String)
                }
                developers {
                    //TODO
                }
            }
        }
    }


    repositories {
        maven {
            name = "maven-central"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

fun Project.semVerString(): Any {
    val version = (project.version as String);
    val index = version.indexOf('-');
    return version.substring(0,index);

}