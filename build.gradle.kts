plugins {
    id("net.researchgate.release") version "2.8.1"
    java
}

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

release {
    tagTemplate = "v\${version}"
}
