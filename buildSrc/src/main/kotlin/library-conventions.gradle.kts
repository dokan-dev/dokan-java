// Define Java Library conventions for this organization.

plugins {
    `java-library`
    `maven-publish`
    id("java-conventions")
}

// Projects have the 'com.example' group by convention
group = "dev.dokan"

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            pom {

            }
        }
    }
    repositories {
        maven {
            name = "myOrgPrivateRepo"
            url = uri("build/my-repo")
        }
    }
}