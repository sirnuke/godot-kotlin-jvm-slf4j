package com.degrendel.godot.slf4j

import godot.global.GD
import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.helpers.MessageFormatter
import org.slf4j.spi.LocationAwareLogger
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class GodotLogger(private val name: String) : Logger
{
  var currentLogLevel = LocationAwareLogger.INFO_INT

  override fun getName() = name

  override fun isTraceEnabled() = isLevelEnabled(LocationAwareLogger.TRACE_INT)
  override fun isTraceEnabled(marker: Marker?): Boolean = isTraceEnabled
  override fun trace(msg: String?) = log(LocationAwareLogger.TRACE_INT, msg, null)
  override fun trace(format: String?, arg: Any?) = formatAndLog(LocationAwareLogger.TRACE_INT, format, arg, null)
  override fun trace(format: String?, arg1: Any?, arg2: Any?) =
    formatAndLog(LocationAwareLogger.TRACE_INT, format, arg1, arg2)

  override fun trace(format: String?, vararg arguments: Any?) =
    formatAndLog(LocationAwareLogger.TRACE_INT, format, arguments)

  override fun trace(msg: String?, t: Throwable?) = log(LocationAwareLogger.TRACE_INT, msg, t)
  override fun trace(marker: Marker?, msg: String?) = trace(msg)
  override fun trace(marker: Marker?, format: String?, arg: Any?) = trace(format, arg)
  override fun trace(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = trace(format, arg1, arg2)
  override fun trace(marker: Marker?, format: String?, vararg argArray: Any?) = trace(format, argArray)
  override fun trace(marker: Marker?, msg: String?, t: Throwable?) = trace(msg, t)

  override fun isDebugEnabled() = isLevelEnabled(LocationAwareLogger.DEBUG_INT)
  override fun isDebugEnabled(marker: Marker?): Boolean = isDebugEnabled
  override fun debug(msg: String?) = log(LocationAwareLogger.DEBUG_INT, msg, null)
  override fun debug(format: String?, arg: Any?) = formatAndLog(LocationAwareLogger.DEBUG_INT, format, arg, null)
  override fun debug(format: String?, arg1: Any?, arg2: Any?) =
    formatAndLog(LocationAwareLogger.DEBUG_INT, format, arg1, arg2)

  override fun debug(format: String?, vararg arguments: Any?) =
    formatAndLog(LocationAwareLogger.DEBUG_INT, format, arguments)

  override fun debug(msg: String?, t: Throwable?) = log(LocationAwareLogger.DEBUG_INT, msg, t)
  override fun debug(marker: Marker?, msg: String?) = debug(msg)
  override fun debug(marker: Marker?, format: String?, arg: Any?) = debug(format, arg)
  override fun debug(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = debug(format, arg1, arg2)
  override fun debug(marker: Marker?, format: String?, vararg arguments: Any?) = debug(format, arguments)
  override fun debug(marker: Marker?, msg: String?, t: Throwable?) = debug(msg, t)

  override fun isInfoEnabled() = isLevelEnabled(LocationAwareLogger.INFO_INT)
  override fun isInfoEnabled(marker: Marker?): Boolean = isInfoEnabled
  override fun info(msg: String?) = log(LocationAwareLogger.INFO_INT, msg, null)
  override fun info(format: String?, arg: Any?) = formatAndLog(LocationAwareLogger.INFO_INT, format, arg, null)
  override fun info(format: String?, arg1: Any?, arg2: Any?) =
    formatAndLog(LocationAwareLogger.INFO_INT, format, arg1, arg2)

  override fun info(format: String?, vararg arguments: Any?) =
    formatAndLog(LocationAwareLogger.INFO_INT, format, arguments)

  override fun info(msg: String?, t: Throwable?) = log(LocationAwareLogger.INFO_INT, msg, t)
  override fun info(marker: Marker?, msg: String?) = info(msg)
  override fun info(marker: Marker?, format: String?, arg: Any?) = info(format, arg)
  override fun info(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = info(format, arg1, arg2)
  override fun info(marker: Marker?, format: String?, vararg arguments: Any?) = info(format, arguments)
  override fun info(marker: Marker?, msg: String?, t: Throwable?) = info(msg, t)

  override fun isWarnEnabled() = isLevelEnabled(LocationAwareLogger.WARN_INT)
  override fun isWarnEnabled(marker: Marker?): Boolean = isWarnEnabled
  override fun warn(msg: String?) = log(LocationAwareLogger.WARN_INT, msg, null)
  override fun warn(format: String?, arg: Any?) = formatAndLog(LocationAwareLogger.WARN_INT, format, arg, null)
  override fun warn(format: String?, arg1: Any?, arg2: Any?) =
    formatAndLog(LocationAwareLogger.WARN_INT, format, arg1, arg2)

  override fun warn(format: String?, vararg arguments: Any?) =
    formatAndLog(LocationAwareLogger.WARN_INT, format, arguments)

  override fun warn(msg: String?, t: Throwable?) = log(LocationAwareLogger.WARN_INT, msg, t)
  override fun warn(marker: Marker?, msg: String?) = warn(msg)
  override fun warn(marker: Marker?, format: String?, arg: Any?) = warn(format, arg)
  override fun warn(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = warn(format, arg1, arg2)
  override fun warn(marker: Marker?, format: String?, vararg arguments: Any?) = warn(format, arguments)
  override fun warn(marker: Marker?, msg: String?, t: Throwable?) = warn(msg, t)

  override fun isErrorEnabled() = isLevelEnabled(LocationAwareLogger.ERROR_INT)
  override fun isErrorEnabled(marker: Marker?): Boolean = isErrorEnabled
  override fun error(msg: String?) = log(LocationAwareLogger.ERROR_INT, msg, null)
  override fun error(format: String?, arg: Any?) = formatAndLog(LocationAwareLogger.ERROR_INT, format, arg, null)
  override fun error(format: String?, arg1: Any?, arg2: Any?) =
    formatAndLog(LocationAwareLogger.ERROR_INT, format, arg1, arg2)

  override fun error(format: String?, vararg arguments: Any?) =
    formatAndLog(LocationAwareLogger.ERROR_INT, format, arguments)

  override fun error(msg: String?, t: Throwable?) = log(LocationAwareLogger.ERROR_INT, msg, t)
  override fun error(marker: Marker?, msg: String?) = error(msg)
  override fun error(marker: Marker?, format: String?, arg: Any?) = error(format, arg)
  override fun error(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = error(format, arg1, arg2)
  override fun error(marker: Marker?, format: String?, vararg arguments: Any?) = error(format, arguments)
  override fun error(marker: Marker?, msg: String?, t: Throwable?) = error(msg, t)

  private fun isLevelEnabled(level: Int) = level >= currentLogLevel

  private fun log(level: Int, message: String?, t: Throwable?)
  {
    if (!isLevelEnabled(level)) return
    val threadName = Thread.currentThread().name
    val levelName = when (level)
    {
      LocationAwareLogger.ERROR_INT -> "ERROR"
      LocationAwareLogger.WARN_INT -> "WARN"
      LocationAwareLogger.INFO_INT -> "INFO"
      LocationAwareLogger.DEBUG_INT -> "DEBUG"
      LocationAwareLogger.TRACE_INT -> "TRACE"
      else -> throw IllegalStateException("Unknown level $level")
    }
    val stack: String = renderStack(t)
    val out = "${
      ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
    } [$threadName][$levelName] $name - $message $stack"
    if (level == LocationAwareLogger.ERROR_INT)
      GD.printErr(out)
    else
      GD.print(out)
  }

  private fun renderStack(t: Throwable?): String
  {
    val stack = t?.stackTrace ?: return ""
    return "\n${stack.joinToString(separator = "\n\t", prefix = "\t")}"
  }

  private fun formatAndLog(level: Int, format: String?, arg1: Any?, arg2: Any?)
  {
    val tp = MessageFormatter.format(format, arg1, arg2)
    log(level, tp.message, tp.throwable)
  }

  private fun formatAndLog(level: Int, format: String?, vararg arguments: Any?)
  {
    val tp = MessageFormatter.arrayFormat(format, arguments)
    log(level, tp.message, tp.throwable)
  }
}
