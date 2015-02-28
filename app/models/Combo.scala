package models

import play.api.libs.json.Json

case class Combo(title: String, items: Seq[String], servings: String, price: Int)

object Combo {
  implicit val comboFormat = Json.format[Combo]
}
