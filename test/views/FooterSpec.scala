package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.{FakeApplication, WithApplication}
import play.i18n.Messages

@RunWith(classOf[JUnitRunner])
class FooterSpec extends Specification {

  "Footer Template".title

  "Footer" should {
    implicit val app = FakeApplication()

    "have content type of 'text/html'" in new WithFooterTemplate {
      contentType(html) must equalTo("text/html")
    }

    "contain content" in new WithFooterTemplate {
      contentAsString(html) must not have length(0)
    }

    "contain copyright message" in new WithFooterTemplate {
      contentAsString(html) must contain(Messages.get("footer.copyright"))
    }
  }

  abstract class WithFooterTemplate(implicit override val app: FakeApplication) extends WithApplication(app) {
    val html = views.html.footer()
  }
}
