package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.{FakeApplication, WithApplication}

@RunWith(classOf[JUnitRunner])
class CarouselSpec extends Specification {

  "Carousel Template".title

  val galleryLink = "<a href".r

  "Carousel with 0 items" should {
    implicit val app = FakeApplication()

    "have content type of 'text/html'" in new WithCarouselTemplate(0, "") {
      contentType(html) must equalTo("text/html")
    }

    "contain content" in new WithCarouselTemplate(0, "") {
      contentAsString(html) must not have length(0)
    }

    "not have any gallery links" in new WithCarouselTemplate(0, "") {
      galleryLink.findAllIn(contentAsString(html)) must have size 0
    }
  }

  "Carousel with 1 item" should {
    implicit val app = FakeApplication()

    "have content type of 'text/html'" in new WithCarouselTemplate(1, "") {
      contentType(html) must equalTo("text/html")
    }

    "contain content" in new WithCarouselTemplate(1, "") {
      contentAsString(html) must not have length(0)
    }

    "have one gallery link" in new WithCarouselTemplate(1, "") {
      galleryLink.findAllIn(contentAsString(html)) must have size 1
    }
  }

  "Carousel with more than 1 item" should {
    implicit val app = FakeApplication()

    "have content type of 'text/html'" in new WithCarouselTemplate(2, "") {
      contentType(html) must equalTo("text/html")
    }

    "contain content" in new WithCarouselTemplate(2, "") {
      contentAsString(html) must not have length(0)
    }

    "have more than one gallery link" in new WithCarouselTemplate(2, "") {
      galleryLink.findAllIn(contentAsString(html)).size must beGreaterThan(1)
    }
  }

  abstract class WithCarouselTemplate(length: Integer, path: String, extension: String = "jpg")
                                     (implicit override val app: FakeApplication)
    extends WithApplication(app) {
    val html = views.html.carousel(length, path, extension)
  }
}
