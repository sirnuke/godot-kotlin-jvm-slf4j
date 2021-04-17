plugins {
    kotlin("jvm") version "1.4.31"
    `maven-publish`
    `java-library`
    id("net.researchgate.release") version "2.8.1"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
}

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

repositories {
    mavenCentral()
}

release {
    tagTemplate = "v\${version}"
}

group = "com.degrendel"

dependencies {
    implementation("com.utopia-rise:godot-library:0.1.2-3.2.3")
    implementation("org.slf4j:slf4j-api:1.7.30")
}

if (project.properties.containsKey("internalMavenURL")) {
    val internalMavenUsername: String by project
    val internalMavenPassword: String by project
    val internalMavenURL: String by project

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
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

    repositories {
        maven {
            credentials {
                username = internalMavenUsername
                password = internalMavenPassword
            }
            url = uri("$internalMavenURL/releases/")
            name = "Internal-Maven-Releases"
        }
        maven {
            credentials {
                username = internalMavenUsername
                password = internalMavenPassword
            }
            url = uri("$internalMavenURL/snapshots/")
            name = "Internal-Maven-Snapshots"
        }
    }
}
