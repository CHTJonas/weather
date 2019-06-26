val finagle = "com.twitter" %% "finagle-http" % "19.5.1"
val xml = "org.scala-lang.modules" %% "scala-xml" % "1.2.0"

lazy val root = (project in file("."))
  .settings(
    organization := "com.github.chtjonas",
    name         := "weather",
    scalaVersion := "2.12.8",
    version      := "2.0.0-SNAPSHOT",
    libraryDependencies += finagle,
    libraryDependencies += xml
  )
