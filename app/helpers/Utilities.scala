package helpers

object Utilities {
  import play.api.mvc.Request

  def isAjax[A](implicit request: Request[A]): Boolean = request.headers.get("X-Requested-With") == Some("XMLHttpRequest")
}
