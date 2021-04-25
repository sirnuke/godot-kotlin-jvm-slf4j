plugins {
  `java-library`
  `maven-publish`
  signing
  id("org.jetbrains.dokka") version "1.4.32"
}

dependencies {
  implementation("com.utopia-rise:godot-library:0.1.4-3.2.3")
  api("org.slf4j:slf4j-api:1.7.30")
}

java {
  withSourcesJar()
}

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
  dependsOn(dokkaHtml)
  archiveClassifier.set("javadoc")
  from(dokkaHtml.outputDirectory)
}

if (project.properties.containsKey("internalMavenURL"))
{
  val internalMavenUsername: String by project
  val internalMavenPassword: String by project
  val internalMavenURL: String by project

  publishing {
    publications {
      create<MavenPublication>("maven") {
        from(components["java"])
        artifact(javadocJar)
        artifactId = "slf4j-godot"
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
else if (project.properties.containsKey("ossrhUsername"))
{
  val ossrhUsername: String by project
  val ossrhPassword: String by project

  publishing {
    publications {
      create<MavenPublication>("maven") {
        artifact(javadocJar)
        pom {
          name.set("SLF4J Godot Logger")
          description.set("SLF4J logger for Godot's Kotlin (JVM) bindings")
          artifactId = "slf4j-godot"
          url.set("https://github.com/sirnuke/godot-kotlin-jvm-slf4j")
          licenses {
            license {
              name.set("MIT License")
              url.set("https://github.com/sirnuke/godot-kotlin-jvm-slf4j/blob/main/LICENSE")
            }
          }
          developers {
            developer {
              id.set("sirnuke")
              name.set("Bryan DeGrendel")
              email.set("bryan@degrendel.com")
            }
          }
          scm {
            connection.set("scm:git:https://github.com/sirnuke/godot-kotlin-jvm-slf4j.git")
            developerConnection.set("scm:git:ssh:git@github.com:godot-kotlin-jvm-slf4j.git")
            url.set("https://github.com/sirnuke/godot-kotlin-jvm-slf4j")
          }
        }
      }
    }
    repositories {
      maven {
        credentials {
          username = ossrhUsername
          password = ossrhPassword
        }
        val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        name = "ossrh"
      }
    }
  }

  signing {
    sign(publishing.publications["maven"])
  }
}
