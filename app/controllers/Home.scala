package controllers

import play.api.mvc.Controller

import models.MenuSupport

object Home extends Controller with MenuSupport {
  import play.api.mvc.Action

  import helpers.Utilities.isAjax

  def index = Action { implicit request =>
    val content = views.html.home()
    if (isAjax) Ok(content)
    else Ok(views.html.index())
  }
}
