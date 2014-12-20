package controllers

import play.api.mvc.{Action, Controller}

import helpers.Utilities
import models.MenuSupport

object Takeout extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.takeout()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
