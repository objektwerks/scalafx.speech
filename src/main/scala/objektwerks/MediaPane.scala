package objektwerks

import java.time.Instant

import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox
import scalafx.scene.media.{Media, MediaPlayer, MediaView}

final class MediaPane(context: Context, store: Store, speech: Speech) extends HBox:
  val media = Media(context.uriAppMp3)
  val mediaPlayer = new MediaPlayer(media):
    autoPlay = true
  val mediaView = MediaView(mediaPlayer)
  
  val labelJoke = Label(context.labelJoke)
  val textJoke = Label("")
  val buttonJoke = new Button():
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
          mediaView.mediaPlayer.value = player
          player.play()
    }

  children = List(labelJoke, textJoke, mediaView, buttonJoke)