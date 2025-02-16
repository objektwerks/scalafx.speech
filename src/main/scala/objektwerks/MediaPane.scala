package objektwerks

import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class MediaPane(context: Context, store: Store) extends HBox:
  println(store)
  
  val labelJoke = Label(context.labelJoke)
  val textJoke = Label("")
  val buttonJoke = new Button():
    text = context.jokeButton