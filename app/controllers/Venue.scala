package controllers

import play.api.mvc.Controller

import models.MenuSupport

object Venue extends Controller with MenuSupport {
  import play.api.mvc.Action

  import helpers.Utilities.isAjax

  def index = Action { implicit request =>
    val content = views.html.venue()
    if (isAjax) Ok(content)
    else Ok(views.html.index(Some(content)))
  }
}
