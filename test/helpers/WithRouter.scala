package helpers

import play.api.test.Helpers._
import play.api.test.{FakeApplication, FakeRequest, WithApplication}

abstract class WithRouter(route: String)
                         (implicit override val app: FakeApplication)
  extends WithApplication(app) {
  val Some(result) = play.api.test.Helpers.route(FakeRequest(GET, route))
}
