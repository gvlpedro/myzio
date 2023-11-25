addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0") // Allows ~reStart
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.1.4") // package the project
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.11.0") // Generates version
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.11.1") // lint
addSbtPlugin("dev.zio" % "zio-sbt-ecosystem" % "0.4.0-alpha.8") // zio
