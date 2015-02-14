name := """emerald-restaurant"""

organization := "com.thomaschoo"

version := "0.3.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb, SbtMongoSeed)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  cache,
  filters,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "nprogress" % "0.1.2",
  "org.webjars" % "requirejs" % "2.1.15",
  "org.webjars" % "requirejs-plugins" % "3ff54566f8",
  "com.mohiva" %% "play-html-compressor" % "0.3.1",
  "com.typesafe" % "config" % "1.2.1",
  "org.julienrf" %% "play-jsmessages" % "1.6.2",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.0.akka23",
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.44.0" % "test",
  "org.webjars" % "font-awesome" % "4.3.0-1"
)

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

// Merge the vendor assets.
Concat.groups := {
  implicit val base: File = (resourceDirectory in Assets).value // public folder
  val exclude: sbt.FileFilter = "" // files to exclude from concat
  def getFiles(finder: PathFinder)(implicit base: File): Either[Seq[String], PathFinder] = group {
    finder.getPaths map (_.replace(base.getPath + "/", ""))
  }
  Seq(
    "stylesheets/vendor.css" -> getFiles((base / "stylesheets" / "vendor") * ("*.css" -- exclude))
  )
}

RjsKeys.mainModule := "app"

// Clean up asset jar.
includeFilter in filter := (
  "*.coffee*" || "*.js*" ||
  "*.less*" || "*.css*" ||
  "*build.txt*"
)

excludeFilter in filter := (
  "*app.js" || "app.js.md5" || "app.js.map" ||
  "*app.min.css" || "app.min.css.md5" ||
  "*vendor.min.css" || "vendor.min.css.md5" ||
  "*gmaps.js" || "gmaps.js.md5" || "gmaps.js.map"
)

pipelineStages in Assets := Seq(concat, cssCompress)

pipelineStages := Seq(rjs, digest, filter)
