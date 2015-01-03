import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import helpers.TestUtilities._
import views._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/bad")) must beNone
    }
  }

  "'/' route" should {
    "respond with index Action" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")

      val content = stripWhiteSpaces(contentAsString(result))
      content must not have length(0)
      val footer = stripWhiteSpaces(contentAsString(views.html.footer()))
      content must contain(footer)
    }
  }

  br
  include(new HomeSpec)

  br
  include(new TakeoutSpec)

  br
  include(new LunchSpec)

  br
  include(new DineSpec)

  br
  include(new FeastSpec)

  br
  include(new WeddingSpec)

  br
  include(new VenueSpec)

  br
  include(new ContactSpec)

  br
  include(new CarouselSpec)

  br
  include(new FooterSpec)
}
