package com.example.myzio.v1.controller

import com.example.myzio.v1.controller.DomainController
import zio.*
import zio.http.*
import zio.json.*
import zio.test.*
import zio.test.Assertion.equalTo

object DomainControllerTest extends ZIOSpecDefault {

  val app = DomainController()
  def spec = {
    suite("Test Domain Controller")(test("Controller should return right text") {
      val req = Request.get(URL(Root / "aaa" /"domain" / "create"))
      assertZIO(app.runZIO(req).map(x => x.body))(equalTo(Response.text("aaa").body))
    })
  }
}
