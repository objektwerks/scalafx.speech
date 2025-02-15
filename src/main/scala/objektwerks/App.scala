package objektwerks

import scalafx.application.JFXApp3

object App extends JFXApp3:
  val context = Context()
  val model = Model()

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context, model).scene
      title = context.windowTitle