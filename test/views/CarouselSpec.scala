package views

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test.Helpers._
import play.api.test.WithApplication

@RunWith(classOf[JUnitRunner])
class CarouselSpec extends Specification {
  "Carousel Template".title

  val galleryLink = "<a href".r

  "Carousel with 0 items" should {
    "render with no gallery links" in new WithCarouselTemplate(0, "") {
      contentType(html) must equalTo("text/html")
      contentAsString(html) must not have length(0)
      galleryLink.findAllIn(contentAsString(html)) must have size 0
    }
  }

  "Carousel with 1 item" should {
    "render with one gallery link" in new WithCarouselTemplate(1, "") {
      contentType(html) must equalTo("text/html")
      contentAsString(html) must not have length(0)
      galleryLink.findAllIn(contentAsString(html)) must have size 1
    }
  }

  "Carousel with more than 1 item" should {
    "render with more than one gallery link" in new WithCarouselTemplate(2, "") {
      contentType(html) must equalTo("text/html")
      contentAsString(html) must not have length(0)
      galleryLink.findAllIn(contentAsString(html)).size must beGreaterThan(1)
    }
  }

  abstract class WithCarouselTemplate(length: Integer, path: String, extension: String = "jpg")
    extends WithApplication {
    val html = views.html.carousel(length, path, extension)
  }

}
