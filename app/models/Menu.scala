package models

import com.typesafe.config.ConfigFactory

import scala.collection.JavaConversions._

case class Menu(items: Seq[MenuItem])
case class MenuItem(url: String, text: String)

trait MenuSupport {
  implicit def menu: Menu = {
    val items =
      ConfigFactory
        .load("menu.conf")
        .getConfigList("menu")
        .map { x =>
          val key = x.entrySet().last.getKey
          MenuItem(key, x.getString(key))
        }

    Menu(items)
  }
}

