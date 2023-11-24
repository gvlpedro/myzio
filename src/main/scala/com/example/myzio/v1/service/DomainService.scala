package com.example.myzio.v1.service

import zio.*
import com.example.myzio.v1.model.domain.Domain

trait DomainService {
  def createDomain(domain: Domain): Task[String]

  def getDomainById(id: String): Task[Option[Domain]]

  def getDomainList(): Task[Option[List[Domain]]]
}

object DomainService {
  def createDomain(domain:Domain): ZIO[DomainService, Throwable, String] =
    ZIO.serviceWithZIO[DomainService](_.createDomain(domain))

  def getDomainById(id: String): ZIO[DomainService, Throwable, Option[Domain]] =
    ZIO.serviceWithZIO[DomainService](_.getDomainById(id))

  def getDomainList(): ZIO[DomainService, Throwable, Option[List[Domain]]] =
    ZIO.serviceWithZIO[DomainService](_.getDomainList())
}
