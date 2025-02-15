package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  os.makeDir.all( buildFilesPath() )
  val filesPath = buildFilesPath()
  logger.info("Initialized store.")

  def buildFilesPath(): Path = os.home / ".speech" / "files"

  def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )

  def writeFile(bytes: Array[Byte], name: String): String =
    supervised:
      assertNotInFxThread
      val path = filesPath / name
      val uri = path.toIO.toURI.toString
      os.write(path, bytes)
      logger.info(s"Write file: $uri")
      uri