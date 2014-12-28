package helpers

import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

abstract class WithRouter(route: String) extends WithApplication {
  val Some(result) = play.api.test.Helpers.route(FakeRequest(GET, route))

  def stripWhiteSpaces(result: String): String = result.replaceAll("\\s", "")
}
