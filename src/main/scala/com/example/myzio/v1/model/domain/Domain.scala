package com.example.myzio.v1.model.domain

import zio.json._

case class Domain(
    id: Option[String],
    name: String
) 

object Domain{
  given codec:JsonCodec[Domain]=DeriveJsonCodec.gen[Domain]
    given JsonEncoder[Domain] = DeriveJsonEncoder.gen[Domain]
    given JsonDecoder[Domain] = DeriveJsonDecoder.gen[Domain]
}