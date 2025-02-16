package objektwerks

import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class MediaPane(context: Context, store: Store) extends HBox:
  println(store)
  
  val labelText = Label(context.labelText)
  val labelSpeech = Label("")