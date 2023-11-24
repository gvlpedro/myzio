package com.example.myzio.v1.controller

import zio._
import zio.http._
import zio.http.Header.{AccessControlAllowMethods, AccessControlAllowOrigin, Origin}
import zio.http.Middleware.{CorsConfig, cors}

object ComposedMiddlewares {
  def apply() = corsRules ++ Middleware.debug ++ Middleware.timeout(5.seconds)

  private val corsRules = cors(
    CorsConfig(
      allowedOrigin = {
        case origin @ Origin.Value(_, host, _) if host.contains("localhost") => Some(AccessControlAllowOrigin.Specific(origin))
        case _                                                               => None
      },
      allowedMethods = AccessControlAllowMethods(Method.PUT, Method.DELETE, Method.GET, Method.POST)
    )
  )
}
