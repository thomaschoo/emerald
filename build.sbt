import com.typesafe.sbt.web.PathMapping
import com.typesafe.sbt.web.pipeline.Pipeline

name := """emerald-chinese-restaurant"""

version := "0.2.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala, net.litola.SassPlugin, SbtWeb)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  cache,
  filters,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "nprogress" % "0.1.2",
  "org.webjars" % "requirejs" % "2.1.15",
  "com.mohiva" %% "play-html-compressor" % "0.3.1",
  "com.typesafe" % "config" % "1.2.1"
)

// Merge the vendor assets.
Concat.groups := {
  implicit val base: File = (resourceDirectory in Assets).value // public folder
  def getFiles(finder: PathFinder)(implicit base: File): Either[Seq[String], PathFinder] = group {
    finder.getPaths map (_.replace(base.getPath + "/", ""))
  }
  Seq(
    "stylesheets/vendor/vendor.css" -> getFiles((base / "stylesheets" / "vendor") * "*.css"),
    "javascripts/vendor/vendor.js" -> getFiles((base / "javascripts" / "vendor") * "*.js")
  )
}

RjsKeys.mainModule := "app"

lazy val imageminify = TaskKey[Pipeline.Stage]("imageminify", "Minify images on the asset pipeline.")

imageminify := { mappings: Seq[PathMapping] =>
  import scala.concurrent.duration._
  import spray.json._
  val shellFile = SbtWeb.copyResourceTo(
    (resourceManaged in imagemin).value,
    getClass.getClassLoader.getResource("imagemin-shell.js"),
    streams.value.cacheDirectory / "copy-resource"
  )
  val (images, other) = mappings partition { x =>
    x._2.endsWith(".png") || x._2.endsWith(".jpg") || x._2.endsWith(".gif")
  }
  streams.value.log("Minifying images with imagemin")
  val op = images map { image =>
    JsArray(JsString(image._1.getPath), JsString(image._2))
  }
  val sourceFileMappings = JsArray(op.toList).toString()
  val buildDir = (resourceManaged in imagemin).value / "build"
  val targetPath = buildDir.getPath
  val jsOptions = JsObject(
    "interlaced" -> JsBoolean(true),
    "optimizationLevel" -> JsNumber(3),
    "progressive" -> JsBoolean(true)
  ).toString()
  SbtJsTask.executeJs(
    state.value,
    JsEngineKeys.EngineType.Node,
    None,
    (WebKeys.nodeModuleDirectories in Plugin).value.map(_.getPath),
    shellFile,
    Seq(sourceFileMappings, targetPath, jsOptions),
    5.minutes
  )
  val postMapping = buildDir.***.get.toSet filter (_.isFile) pair relativeTo(buildDir)
  postMapping ++ other
}

// Clean up asset jar.
includeFilter in filter := (
  "*.coffee*" || "*.js*" ||
  "*.scss*" || "*.css*" ||
  "build.txt*"
)

excludeFilter in filter := (
  "*app.js" || "app.js.md5" || "app.js.map" ||
  "*app.scss" || "app.scss.md5" ||
  "*vendor.js" || "vendor.js.md5" || "vendor.js.map" ||
  "*vendor.min.css" || "vendor.min.css.md5"
)

// For dev.
pipelineStages in Assets := Seq(concat, cssCompress, digest)

// For stage/prod.
//pipelineStages := Seq(concat, cssCompress, rjs, imageminify, digest, filter)
