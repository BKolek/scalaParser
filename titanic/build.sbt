ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "titanic"
  )
import sbt.Keys.libraryDependencies

version := "0.1.0-SNAPSHOT"

scalaVersion := "3.4.0"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.8"
