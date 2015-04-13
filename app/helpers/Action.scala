package helpers

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ActionBuilder, ActionTransformer, Request}

import services.UsersDao

object UserAction extends ActionBuilder[UserRequest] with ActionTransformer[Request, UserRequest] {
  def transform[A](request: Request[A]): Future[UserRequest[A]] = UsersDao.find(request) map (new UserRequest(_, request))
}
