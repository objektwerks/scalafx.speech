package objektwerks

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val jokeUrl = config.getString("joke.url")
  val jokeError = config.getString("joke.error")
  val jokeButton = config.getString("joke.button")

  val labelJoke = config.getString("label.joke")
  val labelPlayer = config.getString("label.player")

  def imageViewSpeech: ImageView = loadImageView("/image/speech.png")
  def imageAppIcon: Image = Image(Image.getClass.getResourceAsStream("/image/icon.png"))

  def uriAppMp3: String = getClass.getResource("/app.mp3").toURI.toString

  private def loadImageView(path: String): ImageView = new ImageView:
    image = Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 22
    fitWidth = 22
    preserveRatio = true
    smooth = true