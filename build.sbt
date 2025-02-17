name := "scalafx.speech"
organization := "objektwerks"
version := "0.7-SNAPSHOT"
scalaVersion := "3.6.4-RC1"
libraryDependencies ++= {
  Seq(
    "com.google.cloud" % "google-cloud-texttospeech" % "2.59.0",
    "com.lihaoyi" %% "os-lib" % "0.11.4",
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
outputStrategy := Some(StdoutOutput)
parallelExecution := false
fork := true

// Begin: Assembly Tasks
lazy val createAssemblyDir = taskKey[File]("Create assembly dir.")
createAssemblyDir := {
  import java.nio.file.*

  val assemblyDir: File = baseDirectory.value / ".assembly"
  val assemblyPath: Path = assemblyDir.toPath

  if (!Files.exists(assemblyPath)) Files.createDirectory(assemblyPath)

  println(s"[createAssemblyDir] assembly dir: $assemblyPath is valid: ${Files.isDirectory(assemblyPath)}")

  assemblyDir
}

lazy val copyAssemblyJar = taskKey[Unit]("Copy assembly jar to assembly dir.")
copyAssemblyJar := {
  import java.nio.file.*

  val assemblyDir: String = createAssemblyDir.value.toString
  val assemblyPath: String = s"${assemblyDir}/${assemblyJarName.value}"

  val source: Path = (assembly / assemblyOutputPath).value.toPath
  val target: Path = Paths.get(assemblyPath)

  println(s"[copyAssemblyJar] source: $source")
  println(s"[copyAssemblyJar] target: $target")

  Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)
}
// End: Assembly Tasks

// Begin: Assembly
assemblyJarName := s"scalafx-speech-${version.value}.jar"
assembly / assemblyMergeStrategy := {
  case PathList("META-INF",  xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
// End: Assembly