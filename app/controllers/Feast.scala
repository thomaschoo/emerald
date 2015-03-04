package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import helpers.Utilities.isAjax
import models.MenuSupport
import services.FeastDao

object Feast extends Controller with MenuSupport {
  def index(offset: Int, limit: Int) = Action.async { implicit request =>
    FeastDao.findAll(offset, limit) map { combos =>
      render {
        case Accepts.Html() =>
          val content = views.html.feast(combos)
          if (isAjax) Ok(content)
          else Ok(views.html.index(Some(content)))
        case Accepts.Json() => Ok(Json.toJson(combos))
      }
    }
  }

  def show(id: String) = Action.async { implicit request =>
    FeastDao.find(id) map { combo =>
      render {
        case Accepts.Json() => Ok(Json.toJson(combo))
      }
    }
  }
}
