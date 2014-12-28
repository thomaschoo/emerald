import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import views._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/bad")) must beNone
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
