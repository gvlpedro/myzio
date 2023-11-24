package com.example.myzio.v1.controller

import com.example.myzio.v1.service.DomainService
import com.example.myzio.v1.model.domain.Domain

import zio.*
import zio.http.*
import zio.http.ChannelEvent.Read
import zio.http.Header.{AccessControlAllowMethods, AccessControlAllowOrigin, Origin}
import zio.http.codec.PathCodec.string

//import zio.logging.fileLogger
//import zio.logging.LogAnnotation
//import zio.config.typesafe.TypesafeConfigProvider
//import zio.{Config, ConfigProvider, ExitCode, Runtime, Scope, ZIO, ZIOAppDefault, ZLayer}

import zio.json.*

object DomainController {
  def apply(): HttpApp[Any] = {
    Routes(
      Method.POST / string("id") / "domain" / "create" ->
        handler { (id: String, _: Request) =>
          for {
            //_ <- ZIO.logInfo("URL:" + req.url.path.toString)
            out <- ZIO.succeed(Response.text(s"Created domain '$id'."))
          } yield out
        }
    ).toHttpApp @@ ComposedMiddlewares()
  }
  /*
  def apply(): HttpApp[Any] = {
    Routes(
      Method.POST / string("id") / "domain" / "create" ->
        handler { (id: String, req: Request) =>
          for {
            //_ <- ZIO.logInfo("URL:" + req.url.path.toString)
            domain <- req.body.asString.map(_.fromJson[Domain])
            r <- domain match {
              case Left(error) =>
                ZIO
                  .debug(s"[CREATE-DOMAIN] Failed to parse the input: $error")
                  .as(
                    http.Response(
                      status = Status.BadRequest,
                      body = Body.fromString(error)
                    )
                  )
              case Right(domain) =>
                DomainService.createDomain(domain).map(out => Response.json(out.toJson))
            }
          } yield r
        }
    ).toHttpApp @@ ComposedMiddlewares()
  }*/
}
