package controllers

import helpers.Utilities
import models.MenuSupport
import play.api.mvc._

object Home extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.home()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index())
  }
}
