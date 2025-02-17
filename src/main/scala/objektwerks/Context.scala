package objektwerks

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val aboutAlertHeaderText = config.getString("about.alert.headerText")
  val aboutAlertContentText = config.getString("about.alert.contentText")

  val menuMenu = config.getString("menu.menu")
  val menuAbout = config.getString("menu.about")
  val menuExit = config.getString("menu.exit")

  val jokeUrl = config.getString("joke.url")
  val jokeError = config.getString("joke.error")
  val jokeButton = config.getString("joke.button")

  val labelJoke = config.getString("label.joke")

  val appMp3 = "app.mp3"
  def loadAppMp3: Array[Byte] = getClass.getResourceAsStream(s"/$appMp3").readAllBytes()

  def imageViewSpeech: ImageView = loadImageView("/image/speech.png")
  def imageAppIcon: Image = Image(Image.getClass.getResourceAsStream("/image/icon.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 22
    fitWidth = 22
    preserveRatio = true
    smooth = true