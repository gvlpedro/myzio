package com.example.myzio

import com.example.myzio.v1.controller.DomainController
import zio.http.Server
import zio._

object Main extends ZIOAppDefault {
  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] = {
    val port = 8084
    println(s"Starting server on http://localhost:$port")

    val httpApps = DomainController()

    Server
      .serve(httpApps)
      .provide(
        Server.defaultWithPort(port)
      )
  }
}
