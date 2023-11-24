package com.example.myzio.v1.service

import com.example.myzio.v1.model.domain.Domain
import com.example.myzio.v1.repository.*
import zio._

case class DomainServiceImpl(domainRepo: DomainRepository) extends DomainService {
  def createDomain(domain:Domain): Task[String] = {
    for {
      isCreated <- domainRepo.saveDomain(domain)
      response <- ZIO.succeed(s"""{ "operation" : $isCreated }""")
    } yield response
  }

  def getDomainById(id: String): Task[Option[Domain]] = {
    for {
      domain <- domainRepo.findDomain(id)
    } yield domain
  }

  def getDomainList(): Task[Option[List[Domain]]] = {
    for {
      maybeList <- domainRepo.listDomains()
    } yield maybeList
  }
}

object DomainServiceImpl {
  def layer: ZLayer[DomainRepository, Nothing, DomainServiceImpl] = ZLayer {
    for {
      repo <- ZIO.service[DomainRepository]
    } yield DomainServiceImpl(repo)
  }
}
