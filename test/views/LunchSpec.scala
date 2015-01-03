package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.FakeApplication
import play.api.test.Helpers._

import helpers.TestUtilities._
import helpers.WithRouter

@RunWith(classOf[JUnitRunner])
class LunchSpec extends Specification {

  "Lunch Route".title

  "'/lunch' route" should {
    implicit val app = FakeApplication()

    "respond with index Action" in new WithRouter("/lunch") {
      status(result) must equalTo(OK)
    }

    "have content type of 'text/html'" in new WithRouter("/lunch") {
      contentType(result) must beSome("text/html")
    }

    "use 'utf-8' encoding" in new WithRouter("/lunch") {
      charset(result) must beSome("utf-8")
    }

    "contain content" in new WithRouter("/lunch") {
      contentAsString(result) must not have length(0)
    }

    "contain the footer" in new WithRouter("/lunch") {
      val content = stripWhiteSpaces(contentAsString(result))
      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)
    }
  }
}
