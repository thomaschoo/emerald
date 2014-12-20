package controllers

import play.api.mvc.{Action, Controller}

import helpers.Utilities
import models.MenuSupport

object Venue extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.venue()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
