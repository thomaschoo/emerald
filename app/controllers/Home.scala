package controllers

import play.api.mvc.{Action, Controller}

import helpers.Utilities
import models.MenuSupport

object Home extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.home()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index())
  }
}
