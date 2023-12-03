package com.example.myzio.v1.controller

import com.example.myzio.v1.model.domain.Domain
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.jsonBody
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ziohttp.*
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZServerEndpoint
import zio.*
import zio.http.HttpApp
import zio.json.*

import java.util.UUID
import scala.collection.mutable

object TapirController {
  // Simulate database:
  val uid1 = "A1t3" // UUID.randomUUID().toString
  val uid2 = UUID.randomUUID().toString

  val database: mutable.Map[String, Domain] = mutable.Map(
    uid1 -> Domain(Some(uid1), "My Domain 1"),
    uid2 -> Domain(Some(uid2), "My Domain 2")
  )

  // Endpoints:

  val helloEndpoint: ZServerEndpoint[Any, Any] = endpoint
    .tag("myzio")
    .name("My zio app")
    .description("This is the basics for start working with zio http.")
    .get
    .in("tapir") // in path
    .out(plainBody[String]) // out expectations
    .serverLogicSuccess[Task](_ => ZIO.succeed("Hello tapir!"))

  val listDomains: ZServerEndpoint[Any, Any] = endpoint
    .tag("domain list")
    .name("Domain list")
    .description("Get all domains in a list")
    .in("tapir" / "list")
    .get
    .out(jsonBody[List[Domain]])
    .serverLogicSuccess[Task](_ => ZIO.succeed(database.values.toList))

  val createDomain: ZServerEndpoint[Any, Any] = endpoint
    .tag("create domain")
    .name("Domain creation")
    .description("Create domain")
    .in("tapir" / "create")
    .post
    .in(jsonBody[Domain])
    .out(jsonBody[String]) // new identifier
    .serverLogicSuccess[Task](request =>
      ZIO.succeed {
        val newId = UUID.randomUUID().toString
        database += (newId -> Domain(Some(newId), request.name))
        newId
      }
    )

  val getDomain: ZServerEndpoint[Any, Any] = endpoint
    .tag("GetDomain")
    .name("Get domain by identifier")
    .description("Get domain by identifier")
    .in("tapir" / "get" / path[String]("id"))
    .get
    .out(jsonBody[Option[Domain]])
    .serverLogicSuccess(id => ZIO.succeed(database.get(id)))

  val endpointsList: List[ZServerEndpoint[Any, Any]] = List(helloEndpoint, listDomains, createDomain, getDomain)

  def swaggerUIServerEndpoints: List[ServerEndpoint[Any, Task]] =
    SwaggerInterpreter().fromServerEndpoints[Task](endpointsList, "my ZIO application", "1.0.0")

  def apply(): HttpApp[Any] =
    ZioHttpInterpreter(ZioHttpServerOptions.default).toHttp(endpointsList ++ swaggerUIServerEndpoints)
}
