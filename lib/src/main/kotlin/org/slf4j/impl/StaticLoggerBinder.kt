package org.slf4j.impl

import com.degrendel.godot.slf4j.GodotLoggerFactory
import org.slf4j.spi.LoggerFactoryBinder

class StaticLoggerBinder : LoggerFactoryBinder
{
  companion object
  {
    private val SINGLETON = StaticLoggerBinder()

    @JvmStatic
    fun getSingleton() = SINGLETON

    val classString: String = StaticLoggerBinder::class.java.name
  }

  private val loggerFactory = GodotLoggerFactory()

  override fun getLoggerFactory() = loggerFactory
  override fun getLoggerFactoryClassStr(): String = classString
}
