package objektwerks

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  def imageViewApp = loadImageView("/image/app.png")
  def imageAppIcon = Image(Image.getClass.getResourceAsStream("/image/icon.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 22
    fitWidth = 22
    preserveRatio = true
    smooth = true