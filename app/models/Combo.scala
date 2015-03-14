package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Combo(id: String, title: String, items: Seq[String], servings: String, price: Int)

object Combo {
  implicit val comboWrites: Writes[Combo] = (
    (__ \ "id").write[String] and
      (__ \ "title").write[String] and
      (__ \ "items").write[Seq[String]] and
      (__ \ "servings").write[String] and
      (__ \ "price").write[Int]
    )(unlift(Combo.unapply))
}
