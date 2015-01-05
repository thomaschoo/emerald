import java.util.concurrent.TimeUnit

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.i18n.Messages
import play.api.test._

import scala.collection.JavaConversions.asScalaBuffer

@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {
    val AltRootPathname = "/home"

    "work from within a browser" in new WithBrowser {
      browser.goTo("http://localhost:" + port)
      browser.$("title").getText must equalTo(Messages("app.title"))
    }

    "allow navigation to different pages from index" in new WithBrowser(WebDriverFactory(Helpers.FIREFOX)) {
      browser.goTo("http://localhost:" + port)

      browser.$("li.menu-item") foreach {
        menuItem =>
          menuItem.click()

          val pathname = menuItem.getAttribute("data-pathname")
          browser.url must equalTo(getAdjustedPathname(pathname))

          val containerId = s"#${pathname.substring(1)}-content"
          browser.await().atMost(10, TimeUnit.SECONDS).until(containerId).isPresent
      }

      def getAdjustedPathname(pathname: String): String = if (pathname == AltRootPathname) "/" else pathname
    }

    "allow browsing to different pages directly" in new WithBrowser {
      browser.goTo("http://localhost:" + port)

      val pathnames = browser.$("li.menu-item").getAttributes("data-pathname").toList
      pathnames foreach {
        pathname =>
          browser.goTo(pathname)
          browser.pageSource must contain(s"${pathname.substring(1)}-content")
      }
    }
  }
}

