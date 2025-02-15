package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  val filesPath = os.home / ".speech" / "files"

  removeFilesPathDir(filesPath)
  makeFilesPathDir(filesPath)

  logger.info("Initialized store.")

  def removeFilesPathDir(path: Path): Unit =
    supervised:
      assertNotInFxThread
      if os.exists(path) then
        os.remove.all(path)

  def makeFilesPathDir(path: Path): Unit =
    supervised:
      assertNotInFxThread
      os.makeDir.all(path)

  def writeFile(bytes: Array[Byte], name: String): String =
    supervised:
      assertNotInFxThread
      val path = filesPath / name
      val uri = path.toIO.toURI.toString
      os.write(path, bytes)
      logger.info(s"Write file: $uri")
      uri

  def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )