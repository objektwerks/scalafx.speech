package objektwerks

import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class MediaPane(context: Context, store: Store, speech: Speech) extends HBox:
  println(store)
  
  val labelJoke = Label(context.labelJoke)
  val textJoke = Label("")
  val buttonJoke = new Button():
    text = context.jokeButton
    onAction = { _ =>
      val joke = getJoke(context)
      val bytes = speech.textToSpeech(joke)
    }

  children = List(labelJoke, textJoke, buttonJoke)