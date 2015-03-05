package services

import scala.concurrent.Future

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.json.BSONFormats._

import reactivemongo.api.QueryOpts
import reactivemongo.bson.{BSONObjectID, BSONDocument}

import models.Combo

object FeastDao {

  private def collection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("combos")

  def find(id: String): Future[Option[Combo]] = {
    val combo =
      for {
        objectId <- BSONObjectID.parse(id)
        c = collection.find(BSONDocument("_id" -> objectId)).one[Combo]
      } yield c

    combo getOrElse Future { None }
  }

  def findAll(offset: Int, limit: Int): Future[List[Combo]] = {
    collection
      .find(BSONDocument("type" -> "feast"))
      .options(QueryOpts(offset, limit))
      .cursor[Combo]
      .collect[List](limit)
  }
}
