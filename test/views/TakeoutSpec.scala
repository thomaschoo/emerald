package views

import org.junit.runner.RunWith
import org.specs2.execute.{AsResult, Result}
import org.specs2.mutable.{Around, Specification}
import org.specs2.runner.JUnitRunner

import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._
import play.test.WithApplication

import helpers.WithRouter

@RunWith(classOf[JUnitRunner])
class TakeoutSpec extends Specification {

  "Takeout Route".title

  "'/takeout' route" should {
    "respond with index Action" in new WithRouter("/takeout") {
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
