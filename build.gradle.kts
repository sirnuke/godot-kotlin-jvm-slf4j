plugins {
  id("net.researchgate.release") version "2.8.1"
  java
  kotlin("jvm") version "1.4.32" apply false
}

allprojects {
  group = "com.degrendel"

  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "org.jetbrains.kotlin.jvm")
}

tasks.register("printVersion") {
  doLast {
    println(project.version)
  }
}

release {
  tagTemplate = "v\${version}"

  (getProperty("git") as net.researchgate.release.GitAdapter.GitConfig).requireBranch = "main"
}

