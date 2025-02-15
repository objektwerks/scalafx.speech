name := "scalafx.speech"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.6.4-RC1"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "23.0.1-R34",
    "org.controlsfx" % "controlsfx" % "11.2.1",
    "ch.qos.logback" % "logback-classic" % "1.5.16"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)