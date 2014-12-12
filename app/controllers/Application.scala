package controllers

import models.MenuSupport
import play.api.mvc._

object Application extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      Ok(views.html.index())
  }

}
