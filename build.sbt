name := """emerald-chinese-restaurant"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, net.litola.SassPlugin, SbtWeb)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  filters,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "nprogress" % "0.1.2",
  "org.webjars" % "requirejs" % "2.1.15",
  "com.mohiva" %% "play-html-compressor" % "0.3.1",
  "com.typesafe" % "config" % "1.2.1"
)

pipelineStages := Seq(rjs)
