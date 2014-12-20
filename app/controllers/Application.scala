package controllers

import play.api.mvc.{Action, Controller}

import models.MenuSupport

object Application extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      Ok(views.html.index())
  }

}
