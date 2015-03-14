package services

import scala.concurrent.Future

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import play.api.libs.json.{Reads, __}
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.BSONFormats._
import play.modules.reactivemongo.json.collection.JSONCollection

import reactivemongo.api.QueryOpts
import reactivemongo.bson.{BSONDocument, BSONObjectID}

import models.Combo

object FeastDao {
  implicit val comboReads: Reads[Combo] = (
    (__ \ "_id").read[BSONObjectID] map (_.stringify) and
      (__ \ "title").read[String] and
      (__ \ "items").read[Seq[String]] and
      (__ \ "servings").read[String] and
      (__ \ "price").read[Int]
    )(Combo.apply _)

  private def collection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("combos")

  def findAll(offset: Int, limit: Int): Future[List[Combo]] = {
    collection
      .find(BSONDocument("type" -> "feast"))
      .options(QueryOpts(offset, limit))
      .cursor[Combo]
      .collect[List](limit)
  }

  def find(id: String): Future[Option[Combo]] = {
    for {
      objectId <- BSONObjectID.parse(id)
      combo = collection.find(BSONDocument("_id" -> objectId)).one[Combo]
    } yield combo
  } getOrElse Future { None }
}
