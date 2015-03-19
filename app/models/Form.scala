package models

import play.api.data.validation.ValidationError
import play.api.libs.json.{Json, JsObject, JsPath}

object Form {
  def toErrorJson(code: Int, errors: Seq[(JsPath, Seq[ValidationError])]): JsObject = {
    Json.obj(
      "code" -> code,
      "message" -> "Validation Failed",
      "errors" -> errors.map { case (path, _) =>
        Json.obj(
          "field" -> path.toString().substring(1),
          "description" -> "Invalid or missing"
        )
      }
    )
  }
}
