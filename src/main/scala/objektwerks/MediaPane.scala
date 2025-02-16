package objektwerks

import java.time.Instant

import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label, TextArea}
import scalafx.scene.layout.{HBox, Priority}
import scalafx.scene.media.{Media, MediaPlayer, MediaView}

final class MediaPane(context: Context, store: Store, speech: Speech) extends HBox:
  val media = Media(context.uriAppMp3)
  val mediaPlayer = MediaPlayer(media)
  val mediaView = MediaView(mediaPlayer)
  
  val labelJoke = new Label():
    prefHeight = 50
    prefWidth = 50
    text = context.labelJoke

  val textJoke = new TextArea():
    prefHeight = 50
    prefWidth = 300
    text = ""

  val buttonJoke = new Button():
    prefHeight = 50
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
  children = List(labelJoke, textJoke, mediaView, buttonJoke)

  HBox.setHgrow(this, Priority.Always)