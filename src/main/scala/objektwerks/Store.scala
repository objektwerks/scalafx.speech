package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  val filesPath = os.home / ".speech" / "files"
  removeFiles()
  makeFilesPathDir(filesPath)
  logger.info("Initialized store.")

  def removeFiles(): Unit =
    supervised:
      if os.exists(filesPath) then
        os.remove.all(filesPath)

  def makeFilesPathDir(path: Path): Unit =
    supervised:
      os.makeDir.all(path)

  def writeFile(bytes: Array[Byte], name: String): String =
    supervised:
      assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )
      val path = filesPath / name
      val uri = path.toIO.toURI.toString
      os.write(path, bytes)
      logger.info(s"Write file: $uri")
      uri