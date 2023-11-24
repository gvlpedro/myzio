import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "3.3.1"
ThisBuild / Test / fork := false
ThisBuild / scalacOptions ++= Seq(
  "-deprecation", // emit warning and location for usages of deprecated APIs
  "-explain", // explain errors in more detail
  "-explain-types", // explain type errors in more detail
  "-feature", // emit warning and location for usages of features that should be imported explicitly
  "-print-lines", // show source code line numbers.
  "-unchecked", // enable additional warnings where generated code depends on assumptions
  "-Ykind-projector", // allow `*` as wildcard to be compatible with kind projector
  // "-Xfatal-warnings", // fail the compilation if there are any warnings
  "-Xmigration", // warn about constructs whose behavior may have changed since version
  "-source:3.1"
)

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies ++= List("com.github.liancheng" %% "organize-imports" % "0.6.0")

def settingsApp = Seq(
  name := "myzio-website",
  Compile / run / mainClass := Option("com.example.myzio.Main"),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  libraryDependencies ++= Seq(
    zioCore,
    zioLogging,
    zioConfigTypeSafe,
    zioJson,
    zioHttp,
    zioTest,
    zioTestSBT,
    zioTestMagnolia,
    zioHttpTestKit
  )
)

lazy val root = (project in file(".")).settings(settingsApp)

addCommandAlias("fmt", "scalafmt; Test / scalafmt; sFix;")
addCommandAlias("fmtCheck", "scalafmtCheck; Test / scalafmtCheck; sFixCheck")
addCommandAlias("sFix", "scalafix OrganizeImports; Test / scalafix OrganizeImports")
addCommandAlias("sFixCheck", "scalafix --check OrganizeImports; Test / scalafix --check OrganizeImports")
