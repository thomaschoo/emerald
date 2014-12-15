package controllers

import com.typesafe.config.ConfigFactory
import helpers.Utilities
import models.{Combo, MenuSupport}
import play.api.mvc._

import scala.collection.JavaConversions._

object Feast extends Controller with MenuSupport {
  implicit def combos: Seq[Combo] = {
    val combos =
      ConfigFactory
        .load("feast.conf")
        .getConfigList("combos")
        .map { x =>
          Combo(
            x.getString("title"),
            x.getStringList("items"),
            x.getString("servings"),
            x.getInt("price")
          )
        }
    combos
  }

  def index = Action {
    implicit request =>
      val content = views.html.feast()
      if (Utilities.isAjax) Ok(content)
      else Ok(views.html.index(Some(content)))
  }
}
