import sbt.*

object Dependencies {
  val zioVersion = "2.0.19"
  val zioHttpVersion = "3.0.0-RC3"

  val zioCore = "dev.zio" %% "zio" % zioVersion
  val zioLogging = "dev.zio" %% "zio-logging" % "2.1.15"
  val zioConfigTypeSafe = "dev.zio" %% "zio-config-typesafe" % "4.0.0-RC16"
  val zioJson = "dev.zio" %% "zio-json" % "0.6.0"

  // Test
  val zioHttp = "dev.zio" %% "zio-http" % zioHttpVersion
  val zioTest = "dev.zio" %% "zio-test" % zioVersion % Test
  val zioTestSBT = "dev.zio" %% "zio-test-sbt" % zioVersion % Test
  val zioTestMagnolia = "dev.zio" %% "zio-test-magnolia" % zioVersion % Test
  val zioHttpTestKit = "dev.zio" %% "zio-http-testkit" % zioHttpVersion % Test
}
