package objektwerks

import java.time.Instant

import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label, TextArea}
import scalafx.scene.layout.{Priority, VBox}
import scalafx.scene.media.{Media, MediaPlayer, MediaView}

final class MediaPane(context: Context,
                      store: Store,
                      speech: Speech) extends VBox:
  val media = Media(context.uriAppMp3)
  val mediaPlayer = new MediaPlayer(media):
    autoPlay = true
  val mediaView = new MediaView(mediaPlayer):
    fitHeight = 50
    fitWidth = 300
  
  val labelJoke = new Label():
    prefHeight = 25
    prefWidth = 50
    text = context.labelJoke

  val textJoke = new TextArea():
    prefHeight = 100
    prefWidth = 300
    text = ""

  val labelPlayer = new Label():
    prefHeight = 25
    prefWidth = 50
    text = context.labelPlayer

  val buttonJoke = new Button():
    prefHeight = 25
    prefWidth = 100
    graphic = context.imageViewSpeech
    text = context.jokeButton
    onAction = { _ =>
      val joke = getJoke(context)
      speech.textToSpeech(joke) match
        case Left(error) => textJoke.text = error.getMessage
        case Right(bytes) => 
          val uri = store.writeFile(bytes, s"${Instant.now.toString}.mp3")
          val media = Media(uri)
          val player = MediaPlayer(media)
          mediaView.mediaPlayer.value.dispose()
          mediaView.mediaPlayer.value = player
          player.play()
    }

  padding = Insets(3, 3, 3, 3)
  spacing = 6
  children = List(labelJoke, textJoke, labelPlayer, mediaView, buttonJoke)

  VBox.setVgrow(this, Priority.Always)