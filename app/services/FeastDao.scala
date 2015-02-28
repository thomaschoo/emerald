package services

import scala.concurrent.Future

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection

import models.Combo

object FeastDao {

  private def collection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("combos")

  def findAll(): Future[List[Combo]] = {
    collection
      .find(Json.obj("type" -> "feast"))
      .cursor[Combo]
      .collect[List]()
  }
}
