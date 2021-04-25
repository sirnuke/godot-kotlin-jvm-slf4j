package com.degrendel.slf4jexample

import godot.Node
import godot.annotation.RegisterClass
import godot.annotation.RegisterFunction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RegisterClass
class Example : Node()
{
  companion object
  {
    private val log by logger()
    private val otherLog = LoggerFactory.getLogger(Example::class.java)
  }

  @RegisterFunction
  override fun _ready()
  {
    log.info("Hello world")
    log.info("Info message with exception", Exception())
    log.error("This is an error")
    otherLog.info("No \$Companion but not as slick")
  }
}

/**
 * Example delegate for creating an SLF4J logger.
 *
 * This is one approach for convenient logger construction.  However, it ideally is used inside a companion object,
 * which means the logger name will be Class$Companion.
 *
 * There are sneakier - though complex and reflection based - ways to do this without the $Companion, if you look
 * around on the internet.
 */
fun <R : Any> R.logger(): Lazy<Logger> = lazy { LoggerFactory.getLogger(this::class.java) }
