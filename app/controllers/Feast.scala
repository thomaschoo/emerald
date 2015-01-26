package controllers

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection

import reactivemongo.api._

import helpers.Utilities.isAjax
import models.JsonFormats._
import models.{Combo, MenuSupport}

object Feast extends Controller with MongoController with MenuSupport {
  def collection: JSONCollection = db.collection[JSONCollection]("combos")

  def index = Action.async { implicit request =>
    findAll() map { combos =>
      if (request.accepts("html/text")) {
        val content = views.html.feast(combos)
        if (isAjax) Ok(content)
        else Ok(views.html.index(Some(content)))
      } else Ok(Json.toJson(combos))
    }
  }

  private def findAll(): Future[List[Combo]] = {
    val cursor: Cursor[Combo] = collection
      .find(Json.obj("type" -> "feast"))
      .cursor[Combo]

    cursor.collect[List]()
  }
}
