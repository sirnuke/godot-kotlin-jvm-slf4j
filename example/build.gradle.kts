plugins {
  id("com.utopia-rise.godot-kotlin-jvm") version "0.1.4-3.2.3"
}

dependencies {
  implementation(project(":lib"))
}

godot {
  isAndroidExportEnabled.set(false)
  dxToolPath.set("dx")
}
