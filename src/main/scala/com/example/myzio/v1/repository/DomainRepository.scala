package com.example.myzio.v1.repository

import com.example.myzio.v1.model.domain.Domain
import com.example.myzio.v1.repository.*
import zio.*

trait DomainRepository {
  def saveDomain(domain:Domain): Task[Boolean]

  def findDomain(id: String): Task[Option[Domain]]

  def listDomains(): Task[Option[List[Domain]]]
}

object DomainRepository {
  def saveDomain(domain:Domain): ZIO[DomainRepository, Throwable, Boolean] =
    ZIO.serviceWithZIO[DomainRepository](_.saveDomain(domain))

  def findDomain(id: String, domainId: String): ZIO[DomainRepository, Throwable, Option[Domain]] =
    ZIO.serviceWithZIO[DomainRepository](_.findDomain(id))

  def listDomains(): ZIO[DomainRepository, Throwable, Option[List[Domain]]] =
    ZIO.serviceWithZIO[DomainRepository](_.listDomains())
}
