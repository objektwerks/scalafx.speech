package objektwerks

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scalafx.application.JFXApp3

object App extends JFXApp3 with LazyLogging:
  logger.info("Starting app ...")

  val config = ConfigFactory.load("/app.conf")
  val context = Context(config)
  val model = Model()

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context, model).scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons += context.imageAppIcon