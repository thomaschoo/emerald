package controllers

import helpers.Utilities
import models.MenuSupport
import play.api.mvc._

object Lunch extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.lunch()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
