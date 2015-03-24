package controllers

import scala.concurrent.Future
import scala.util.{Failure, Success}

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import helpers.Utilities.isAjax
import models.{ComboForm, Form, MenuSupport}
import services.FeastDao

object Feast extends Controller with MenuSupport {
  val CreateFailed = 41
  val UpdateFailed = 42

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
        case Accepts.Json() => combo match {
          case Some(c) => Ok(Json.toJson(c))
          case None => NotFound(Json.parse("{}"))
        }
      }
    }
  }

  def create() = Action.async(parse.json) { implicit request =>
    Json.fromJson[ComboForm](request.body).fold(
      invalid => Future.successful(BadRequest(Form.toErrorJson(CreateFailed, invalid))),
      form => FeastDao.insert(form) map (_ => Created)
    )
  }

  def update(id: String) = Action.async(parse.json) { implicit request =>
    Json.fromJson[ComboForm](request.body).fold(
      invalid => Future.successful(BadRequest(Form.toErrorJson(UpdateFailed, invalid))),
      form => FeastDao.update(form) match {
        case Success(lastError) => lastError map (_ => Ok)
        case Failure(ex) => Future.successful(BadRequest(ex.getMessage))
      }
    )
  }
}
