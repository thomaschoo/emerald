package controllers

import play.api.mvc.Controller

import models.MenuSupport

object Application extends Controller with MenuSupport {
  import play.api.Play.current
  import play.api.cache.Cached
  import play.api.mvc.Action

  def index = Cached("index") {
    Action { implicit request =>
      Ok(views.html.index())
    }
  }
}
