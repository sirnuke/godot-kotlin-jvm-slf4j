plugins {
    kotlin("jvm") version "1.4.32"
    id("com.utopia-rise.godot-kotlin-jvm") version "0.1.2-3.2.3"
}

dependencies {
    implementation(project(":lib"))
}

repositories {
    mavenCentral()
}

godot {
    isAndroidExportEnabled.set(false)
    dxToolPath.set("dx")
}
