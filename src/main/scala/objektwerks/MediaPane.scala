package objektwerks

import java.time.Instant

import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label, TextArea}
import scalafx.scene.layout.{Priority, VBox}
import scalafx.scene.media.{Media, MediaPlayer}

final class MediaPane(context: Context,
                      store: Store,
                      speech: Speech) extends VBox:
  val uri = store.writeFile(context.loadAppMp3, context.appMp3)
  var mediaPlayer = new MediaPlayer( Media(uri) ):
    autoPlay = true
  
  val labelJoke = new Label():
    prefHeight = 25
    prefWidth = 50
    text = context.labelJoke

  val textJoke = new TextArea():
    prefHeight = 100
    prefWidth = 300
    text = ""

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
          mediaPlayer.dispose()
          mediaPlayer = new MediaPlayer( Media(uri) ):
            autoPlay = true
    }

  padding = Insets(3, 3, 3, 3)
  spacing = 6
  children = List(labelJoke, textJoke, buttonJoke)

  VBox.setVgrow(this, Priority.Always)