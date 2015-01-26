package controllers

import play.api.Play.current
import play.api.cache.Cached
import play.api.mvc.{Action, Controller}

import models.MenuSupport

object Application extends Controller with MenuSupport {
  def index = Cached("index") {
    Action { implicit request =>
      Ok(views.html.index())
    }
  }
}
