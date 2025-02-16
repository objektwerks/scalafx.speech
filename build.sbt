name := "scalafx.speech"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.6.4-RC1"
libraryDependencies ++= {
  Seq(
    "com.google.cloud" % "google-cloud-texttospeech" % "2.59.0",
    "com.lihaoyi" %% "os-lib" % "0.11.4-M6",
    "com.softwaremill.ox" %% "core" % "0.5.11",
    "org.scalafx" %% "scalafx" % "23.0.1-R34",
    "com.lihaoyi" %% "ujson" % "4.1.0",
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.16",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)