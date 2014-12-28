package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.WithApplication
import play.i18n.Messages

@RunWith(classOf[JUnitRunner])
class FooterSpec extends Specification {

  "Footer Template".title

  "Footer" should {
    "render" in new WithApplication {
      val html = views.html.footer()
      contentType(html) must equalTo("text/html")
      contentAsString(html) must not have length(0)
      contentAsString(html) must contain(Messages.get("footer.copyright"))
    }
  }
}
