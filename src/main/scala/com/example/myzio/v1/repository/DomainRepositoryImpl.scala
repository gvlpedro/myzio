package com.example.myzio.v1.repository

import com.example.myzio.v1.model.domain.Domain
import com.example.myzio.v1.repository.*
import zio.*

case class DomainRepositoryImpl(refDomains: Ref[Map[String, Domain]]) extends DomainRepository {
  def saveDomain(domain: Domain): UIO[Boolean] =
    for {
      maybeDomain <- refDomains.get.map(m => m.get(domain.id))
      added <- maybeDomain match {
        case None =>
          for {
            keyValue  <- ZIO.succeed(domain.id -> domain)
            _         <- refDomains.update(_ + keyValue)
            result    <- ZIO.succeed(false)
          } yield result
        case Some(_) => ZIO.succeed(false)
      }
    } yield added

  def findDomain(id: String): UIO[Option[Domain]] = {
    for {
      maybeDomain <- ZIO.succeed(None) // TODO
    } yield maybeDomain
  }

  def listDomains(): Task[Option[List[Domain]]] = {
    ZIO.succeed(None) // TODO
  }
}

object DomainRepositoryImpl {
  private val defaultDomainMap: Map[String,Domain] = Map()

  def layer: ZLayer[Any, Nothing, DomainRepositoryImpl] =
    ZLayer.fromZIO(Ref.make(defaultDomainMap).map(new DomainRepositoryImpl(_)))
}
