package controllers

import jsmessages.api.JsMessages

import play.api.Play.current
import play.api.cache.Cached
import play.api.mvc.{Action, Controller}

import helpers.Utilities._
import models.MenuSupport


object Contact extends Controller with MenuSupport {

  def index = Action {
    implicit request =>
      val content = views.html.contact()
      if (isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }

  val jsMessages = JsMessages.filtering(_.startsWith("contact."))
  def messages = Cached("contact.messages") {
    Action {
      implicit request =>
        Ok(jsMessages(Some("window.Messages")))
    }
  }
}
