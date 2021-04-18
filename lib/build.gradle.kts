plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.4.31"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
}

group = "com.degrendel"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.utopia-rise:godot-library:0.1.2-3.2.3")
    api("org.slf4j:slf4j-api:1.7.30")
}

if (project.properties.containsKey("internalMavenURL")) {
    val internalMavenUsername: String by project
    val internalMavenPassword: String by project
    val internalMavenURL: String by project

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                artifactId = "godot-kotlin-jvm-slf4j"
            }
        }
        repositories {
            maven {
                credentials {
                    username = internalMavenUsername
                    password = internalMavenPassword
                }
                val releasesRepoUrl = uri("$internalMavenURL/releases/")
                val snapshotsRepoUrl = uri("$internalMavenURL/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                name = "Internal-Maven"
            }
        }
    }
}
