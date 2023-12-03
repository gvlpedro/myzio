package com.example.myzio.v1.controller

import zio.*
import zio.http.*
import zio.http.codec.PathCodec.string

object DomainController {
  def apply(): HttpApp[Any] = {
    Routes(
      Method.GET / string("id") / "domain" / "create" ->
        handler { (id: String, req: Request) =>
          for {
            _ <- ZIO.logInfo("URL:" + req.url.path.toString)
            // _ <- req.body.asString.map(Response.text(_))
            out <- ZIO.succeed(Response.text(id))
          } yield out
        },
      Method.GET / "" -> handler(ZIO.succeed(Response.text("working...")))
    ).toHttpApp @@ ComposedMiddlewares()
  }
}
