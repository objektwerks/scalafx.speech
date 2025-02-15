package objektwerks

import com.typesafe.scalalogging.LazyLogging

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  val filesPath = os.home / ".speech" / "files"
  os.makeDir.all(filesPath)
  logger.info("Initialized store.")

  def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )

  def writeFile(bytes: Array[Byte], name: String): String =
    supervised:
      assertNotInFxThread
      val path = filesPath / name
      val uri = path.toIO.toURI.toString
      os.write(path, bytes)
      logger.info(s"Write file: $uri")
      uri