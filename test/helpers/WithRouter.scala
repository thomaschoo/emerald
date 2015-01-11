package helpers

import play.api.test.{ FakeApplication, WithApplication }

abstract class WithRouter(route: String)
                         (implicit override val app: FakeApplication)
  extends WithApplication(app) {
  import play.api.test.FakeRequest
  import play.api.test.Helpers._

  val Some(result) = play.api.test.Helpers.route(FakeRequest(GET, route))
}
