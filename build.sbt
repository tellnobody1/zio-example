lazy val root = project
  .in(file("."))
  .settings(
    scalaVersion := "3.3.0-RC3",
    libraryDependencies ++= Seq(
      "dev.zio"    %% "zio-test-sbt"        % zio     % Test,
      "dev.zio"    %% "zio-test"            % zio     % Test,
      "dev.zio"    %% "zio"                 % zio,
      "io.circe"   %% "circe-core"          % circe,
      "org.http4s" %% "http4s-blaze-server" % http4s,
      "org.http4s" %% "http4s-circe"        % http4s,
      "org.http4s" %% "http4s-dsl"          % http4s,
    ),
    scalacOptions ++= Seq("-Ykind-projector")
  )
  .dependsOn(zioInteropCatsJVM)

lazy val zioInteropCats = crossProject(JVMPlatform)
  .in(file("interop-cats/zio-interop-cats"))
  .settings(
    scalaVersion := "3.3.0-RC3",
    scalacOptions ++= Seq("-Ykind-projector"),
    libraryDependencies ++= Seq(
      "co.fs2"        %%% "fs2-core"    % "2.5.11",
      "dev.zio"       %%% "zio-streams" % zio,
      "org.typelevel" %%% "cats-effect" % "2.5.5",
      "org.typelevel" %%% "cats-mtl"    % "1.2.1",
    ),
  )

lazy val zioInteropCatsJVM = zioInteropCats.jvm

Global / onChangedBuildSource := ReloadOnSourceChanges

val circe  = "0.14.3"
val http4s = "0.22.15"
val zio    = "1.0.18"
