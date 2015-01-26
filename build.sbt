name := """emerald-restaurant"""

organization := "com.thomaschoo"

version := "0.2.1"

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
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.44.0" % "test"
)

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

// Merge the vendor assets.
Concat.groups := {
  implicit val base: File = (resourceDirectory in Assets).value // public folder
  val exclude: sbt.FileFilter = "gmaps.js" // files to exclude from concat
  def getFiles(finder: PathFinder)(implicit base: File): Either[Seq[String], PathFinder] = group {
    finder.getPaths map (_.replace(base.getPath + "/", ""))
  }
  Seq(
    "stylesheets/vendor/vendor.css" -> getFiles((base / "stylesheets" / "vendor") * ("*.css" -- exclude)),
    "javascripts/vendor/vendor.js" -> getFiles((base / "javascripts" / "vendor") * ("*.js" -- exclude))
  )
}

RjsKeys.mainModule := "app"

// Clean up asset jar.
includeFilter in filter := (
  "*.coffee*" || "*.js*" ||
  "*.less*" || "*.css*" ||
  "build.txt*"
)

excludeFilter in filter := (
  "*app.js" || "app.js.md5" || "app.js.map" ||
  "*app.min.css" || "app.min.css.md5" ||
  "*vendor.js" || "vendor.js.md5" || "vendor.js.map" ||
  "*gmaps.js" || "gmaps.js.md5" || "gmaps.js.map" ||
  "*vendor.min.css" || "vendor.min.css.md5"
)

pipelineStages in Assets := Seq(concat, cssCompress)

pipelineStages := Seq(rjs, digest, filter)
