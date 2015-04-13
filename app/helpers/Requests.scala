package helpers

import play.api.mvc.{Request, WrappedRequest}

import models.User

case class UserRequest[A](user: Option[User], request: Request[A]) extends WrappedRequest[A](request)
