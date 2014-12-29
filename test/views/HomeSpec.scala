package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.{FakeApplication, FakeRequest}
import play.api.test.Helpers._

import helpers.WithRouter

@RunWith(classOf[JUnitRunner])
class HomeSpec extends Specification {

  "Home Route".title

  "'/home' route" should {
    implicit val app = FakeApplication()

    "respond with index Action" in new WithRouter("/home") {
      status(result) must equalTo(OK)
    }

    "have content type of 'text/html'" in new WithRouter("/home") {
      contentType(result) must beSome("text/html")
    }

    "use 'utf-8' encoding" in new WithRouter("/home") {
      charset(result) must beSome("utf-8")
    }

    "contain content" in new WithRouter("/home") {
      contentAsString(result) must not have length(0)
    }

    "contain the footer" in new WithRouter("/home") {
      val content = stripWhiteSpaces(contentAsString(result))
      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)
    }

    "be identical to '/' route" in new WithRouter("/home") {
      val Some(result2) = route(FakeRequest(GET, "/"))
      val content = stripWhiteSpaces(contentAsString(result))
      val content2 = contentAsString(result2)
      content must equalTo(content2).ignoreSpace
    }
  }
}
