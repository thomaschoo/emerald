package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HomeSpec extends Specification {
  import play.api.test.Helpers._
  import play.api.test.{ FakeRequest, WithApplication }

  import helpers.TestUtilities._

  "Home Route".title

  "'/home' route" should {
    "respond with index Action" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/home"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")

      val content = stripWhiteSpaces(contentAsString(result))
      content must not have length(0)

      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)

      val Some(result2) = route(FakeRequest(GET, "/"))
      val content2 = contentAsString(result2)
      content must equalTo(content2).ignoreSpace
    }
  }
}
