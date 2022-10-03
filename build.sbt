name := """smart-tpv-server"""
organization := "com.smartstudio"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean).disablePlugins(PlayFilters)

scalaVersion := "2.12.4"

resolvers += "tuxburner.github.io" at "http://tuxburner.github.io/repo"

libraryDependencies ++= Seq(
  guice,
  filters,
  evolutions,
  jdbc,
  ws,
  ehcache,
  "org.postgresql" % "postgresql" % "42.2.1",
  "io.ebean" % "ebean" % "10.4.7" % "provided",
  "javax.persistence" % "persistence-api" % "1.0.2",
  "org.projectlombok" % "lombok" % "1.16.8",
  "org.jodd" % "jodd-mail" % "4.1.4",
  "org.julienrf" %% "play-jsmessages" % "3.0.0",
  "org.apache.poi" % "poi-ooxml" % "3.17",
  "com.monitorjbl" % "xlsx-streamer" % "1.2.1",
  "com.github.tuxBurner" %% "play-akkajobs" % "2.6.1",
  "com.auth0" % "java-jwt" % "3.3.0",
  "com.h2database" % "h2" % "1.4.192"
)

import com.typesafe.sbt.packager.MappingsHelper._

mappings in Universal ++= directory(baseDirectory.value / "public")

// Apply RequireJS optimizations, create a checksum, and zip each asset
//pipelineStages := Seq(rjs, digest, gzip)
