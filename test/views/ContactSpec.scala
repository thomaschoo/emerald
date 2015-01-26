package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import helpers.TestUtilities._

@RunWith(classOf[JUnitRunner])
class ContactSpec extends Specification {
  "Contact Route".title

  "'/contact' route" should {
    "respond with index Action" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/contact"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")

      val content = stripWhiteSpaces(contentAsString(result))
      content must not have length(0)

      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)
    }
  }

  "'/contact/messages.js' route" should {
    "respond with messages Action" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/contact/messages.js"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/javascript")
      charset(result) must beSome("utf-8")
    }
  }
}
