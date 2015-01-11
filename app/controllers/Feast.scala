package controllers

import play.api.mvc.Controller

import models.MenuSupport

object Feast extends Controller with MenuSupport {
  import com.typesafe.config.ConfigFactory

  import play.api.mvc.Action

  import scala.collection.JavaConversions.asScalaBuffer

  import helpers.Utilities.isAjax
  import models.Combo

  val combos: Seq[Combo] =
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

  def index = Action { implicit request =>
    val content = views.html.feast(combos)
    if (isAjax) Ok(content)
    else Ok(views.html.index(Some(content)))
  }
}
