package objektwerks

import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class MediaPane(context: Context, store: Store) extends HBox:
  println(store)
  
  val labelText = Label(context.labelText)
  val labelJoke = Label("")
  val buttonJoke = new Button():
    text = context.jokeButton