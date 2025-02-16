package objektwerks

import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.VBox

final class View(context: Context):
  println(context)

  val rootPane = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List()

  val scene = new Scene:
    root = rootPane
    stylesheets = List("/style.css")