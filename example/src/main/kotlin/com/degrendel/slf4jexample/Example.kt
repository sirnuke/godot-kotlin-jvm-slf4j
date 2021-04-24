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
  }

  @RegisterFunction
  override fun _ready()
  {
	log.info("Hello world")
	log.info("Info message with exception", Exception())
	log.error("This is an error")
  }
}

fun <R : Any> R.logger(): Lazy<Logger> = lazy { LoggerFactory.getLogger(this::class.java) }
