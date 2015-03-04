package services

import scala.concurrent.Future

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.json.BSONFormats._

import reactivemongo.api.QueryOpts
import reactivemongo.bson.BSONObjectID

import models.Combo

object FeastDao {

  private def collection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("combos")

  def find(id: String): Future[Option[Combo]] = {
    collection
      .find(Json.obj("_id" -> BSONObjectID(id)))
      .cursor[Combo]
      .headOption
  }

  def findAll(offset: Int, limit: Int): Future[List[Combo]] = {
    collection
      .find(Json.obj("type" -> "feast"))
      .options(QueryOpts(offset, limit))
      .cursor[Combo]
      .collect[List](limit)
  }
}
