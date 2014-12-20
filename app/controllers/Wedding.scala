package controllers

import play.api.mvc.{Action, Controller}

import helpers.Utilities
import models.MenuSupport

object Wedding extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.wedding()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
