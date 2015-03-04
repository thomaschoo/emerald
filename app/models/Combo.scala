package models

import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.modules.reactivemongo.json.BSONFormats._

import reactivemongo.bson.BSONObjectID

case class Combo(id: String, title: String, items: Seq[String], servings: String, price: Int)

object Combo {
  val comboReads: Reads[Combo] = (
    (__ \ "_id").read[BSONObjectID] map(_.stringify) and
      (__ \ "title").read[String] and
      (__ \ "items").read[Seq[String]] and
      (__ \ "servings").read[String] and
      (__ \ "price").read[Int]
    )(Combo.apply _)

  val comboWrites: Writes[Combo] = (
    (__ \ "id").write[String] and
      (__ \ "title").write[String] and
      (__ \ "items").write[Seq[String]] and
      (__ \ "servings").write[String] and
      (__ \ "price").write[Int]
    )(unlift(Combo.unapply))

  implicit val comboFormat: Format[Combo] = Format(comboReads, comboWrites)
}
