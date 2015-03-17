package models

import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Combo(id: String, title: String, items: Seq[String], servings: String, price: Int)
case class ComboForm(id: Option[String], title: String, items: Seq[String], servings: String, price: Int)

object Combo {
  implicit val comboWrites: Writes[Combo] = (
    (__ \ "id").write[String] and
      (__ \ "title").write[String] and
      (__ \ "items").write[Seq[String]] and
      (__ \ "servings").write[String] and
      (__ \ "price").write[Int]
    )(unlift(Combo.unapply))
}

object ComboForm {
  implicit val comboFormFormat = Json.format[ComboForm]

  def toErrorJson(code: Int, errors: Seq[(JsPath, Seq[ValidationError])]): JsObject = {
    Json.obj(
      "code" -> code,
      "message" -> "Validation Failed",
      "errors" -> errors.map { case (x, _) =>
        Json.obj(
          "field" -> x.toString().substring(1),
          "description" -> "Invalid or missing"
        )
      }
    )
  }
}
