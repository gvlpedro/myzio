package com.example.myzio.v1.controller

import zio.http.Header.{AccessControlAllowMethods, AccessControlAllowOrigin, Origin}
import zio.http.Method
import zio.http.Middleware.{CorsConfig, cors}

object Cors {
  val config: CorsConfig =
    CorsConfig(
      allowedOrigin = {
        case origin @ Origin.Value(_, host, _) if host.contains("localhost") => Some(AccessControlAllowOrigin.Specific(origin))
        case _                                                               => None
      },
      allowedMethods = AccessControlAllowMethods(Method.PUT, Method.DELETE, Method.GET, Method.POST)
    )
}
