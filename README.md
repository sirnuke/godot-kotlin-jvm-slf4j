# SLF4J Godot

A simple logger implementation for SLF4J that redirects logging to Godot's print and printerr.  Intended for use with
the [Godot Kotlin JVM bindings](https://github.com/utopia-rise/godot-kotlin-jvm/).

## Usage

Add the following to your Gradle configuration:

### Gradle (Groovy)

```Groovy
dependencies {
    implementation 'com.degrendel:slf4j-godot:0.1.0'
}

```

### Gradle (Kotlin)

```kotlin
dependencies {
  implementation("com.degrendel:slf4j-godot:0.1.0")
}
```

Load the logger with the standard procedure, such as

```kotlin
companion {
  private val log = LoggerFactory.getLogger(ParentClass::class.java)
}
```

See example module for detailed usage.  Once constructed, use as any normal SLF4J logger.

## Customization

If you need output at a level lower than Info, I recommend casting the logger to GodotLogger and setting
currentLogLevel to LocationAwareLogger.TRACE_INT or LocationAwareLogger.DEBUG_INT.  If you need to customize the output
format or overall output level, I suggest copying the three classes in the lib directory to your project and making the
appropriate changes to GodotLogger.

Or if there's a serious use-case, complain in the issues and I'll write an appender implementation for logback-classic.
