package objektwerks

import java.time.Instant

import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox
import scalafx.scene.media.{Media, MediaPlayer}

final class MediaPane(context: Context, store: Store, speech: Speech) extends HBox:
  println(store)
  
  val labelJoke = Label(context.labelJoke)
  val textJoke = Label("")
  val buttonJoke = new Button():
    text = context.jokeButton
    onAction = { _ =>
      val joke = getJoke(context)
      speech.textToSpeech(joke) match
        case Left(error) => textJoke.text = error.getMessage
        case Right(bytes) => 
          val uri = store.writeFile(bytes, s"${Instant.now.toString}.mp3")
          val media = Media(uri)
          MediaPlayer(media).play()
    }

  children = List(labelJoke, textJoke, buttonJoke)