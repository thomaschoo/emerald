name := """emerald-chinese-restaurant"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, net.litola.SassPlugin)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  filters,
  "com.mohiva" %% "play-html-compressor" % "0.3.1",
  "com.typesafe" % "config" % "1.2.1"
)
