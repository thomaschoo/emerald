package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._

import helpers.WithRouter

@RunWith(classOf[JUnitRunner])
class ContactSpec extends Specification {

  "Contact Route".title

  "'/contact' route" should {
    "respond with index Action" in new WithRouter("/contact") {
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")

      val content = stripWhiteSpaces(contentAsString(result))
      content must not have length(0)

      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)
    }
  }
}
