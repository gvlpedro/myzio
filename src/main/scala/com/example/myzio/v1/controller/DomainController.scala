package com.example.myzio.v1.controller

import com.example.myzio.v1.service.DomainService
import com.example.myzio.v1.model.domain.Domain

import zio.*
import zio.http.*
import zio.http.ChannelEvent.Read
import zio.http.Header.{AccessControlAllowMethods, AccessControlAllowOrigin, Origin}
import zio.http.codec.PathCodec.string

import zio.logging.fileLogger
import zio.logging.LogAnnotation
import zio.config.typesafe.TypesafeConfigProvider
import zio.{Config, ConfigProvider, ExitCode, Runtime, Scope, ZIO, ZIOAppDefault, ZLayer}

import zio.json.*

object DomainController {
  def apply(): HttpApp[Any] = {
    Routes(
      Method.GET / string("id") / "domain" / "create" ->
        handler { (id: String, req: Request) =>
          for {
            _ <- ZIO.logInfo("URL:" + req.url.path.toString)
            out <- ZIO.succeed(Response.text(id))
          } yield out
        }
    ).toHttpApp @@ ComposedMiddlewares()
  }
}
