package helpers

import play.api.mvc.Request

object Utilities {

  def isAjax[A](implicit request : Request[A]) = request.headers.get("X-Requested-With") == Some("XMLHttpRequest")
}
