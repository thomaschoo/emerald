package models

case class Menu(items: Seq[MenuItem])
case class MenuItem(url: String, text: String)

trait MenuSupport {
  import play.api.Play.current
  import play.api.i18n.{Lang, Messages}

  implicit def menu(implicit lang: Lang): Menu = {

    val messages = Messages.messages filter {
      case (k, v) =>
        if (k.equals("default") || k.equals(lang.language)) v.keys exists (_.startsWith("menu."))
        else false
    }

    val menuMessages = messages.getOrElse(lang.language, messages("default")) filter {
      case (k, v) => k.startsWith("menu.")
    }

    val items = menuMessages.keys.toList.sorted map { k =>
      MenuItem(
        url = s"/${k.substring(k.lastIndexOf(".") + 1)}",
        text = menuMessages(k)
      )
    }

    Menu(items)
  }
}
