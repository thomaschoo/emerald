package models

import play.api.libs.json.Json

object JsonFormats {
  implicit val comboFormat = Json.format[Combo]
}
