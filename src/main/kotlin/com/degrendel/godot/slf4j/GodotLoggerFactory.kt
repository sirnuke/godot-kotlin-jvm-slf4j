package com.degrendel.godot.slf4j

import org.slf4j.ILoggerFactory
import org.slf4j.Logger
import java.util.concurrent.ConcurrentHashMap

class GodotLoggerFactory : ILoggerFactory {
    private val loggerMap = ConcurrentHashMap<String, Logger>()

    override fun getLogger(name: String) = loggerMap.computeIfAbsent(name) { GodotLogger(name) }
}
