package controllers

import helpers.Utilities
import models.MenuSupport
import play.api.mvc._

object Feast extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.feast()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
