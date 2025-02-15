package objektwerks

import scalafx.application.JFXApp3

object App extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View( Model() ).scene
      title = "App"