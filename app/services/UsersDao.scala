package services

import scala.concurrent.Future

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import play.api.libs.json.{Json, Reads, __}
import play.api.mvc.RequestHeader
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.BSONFormats._
import play.modules.reactivemongo.json.collection.JSONCollection

import models.User
import reactivemongo.bson.BSONObjectID

object UsersDao {
  implicit val userReads: Reads[User] = (
    (__ \ "_id").read[BSONObjectID] map (_.stringify) and
      (__ \ "firstName").read[String] and
      (__ \ "lastName").read[String] and
      (__ \ "key").read[String] and
      (__ \ "createdDt").read[Int] and
      (__ \ "updatedDt").read[Int]
    )(User.apply _)

  private def collection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("users")

  def find(req: RequestHeader): Future[Option[User]] = req.headers.get("X-Api-Key") match {
    case Some(key) => collection.find(Json.obj("key" -> key)).one[User]
    case None => Future.successful(None)
  }

  def find(id: String): Future[Option[User]] = {
    for {
      objectId <- BSONObjectID.parse(id)
      combo = collection.find(Json.obj("_id" -> objectId)).one[User]
    } yield combo
  } getOrElse Future.successful(None)
}
