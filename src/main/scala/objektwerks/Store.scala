package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  os.makeDir.all( buildFilesPath() )

  private val filesPath = buildFilesPath()

  logger.info("Initialized store.")

  private def buildFilesPath(): Path = os.home / ".speech" / "files"

  private def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )

  def writeFile(bytes: Array[Byte], file: String): Unit =
    supervised:
      assertNotInFxThread
      os.write.over(filesPath / file, bytes)
      logger.info(s"Write file: $file")