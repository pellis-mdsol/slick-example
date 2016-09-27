name := "slick-example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val slickV = "3.1.1"

  Seq(
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "com.typesafe.slick" %% "slick" % slickV,
    "com.typesafe.slick" %% "slick-codegen" % slickV,
    "org.flywaydb" % "flyway-core" % "4.0.3"
  ) map (_ % "compile")
}

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.0",
    "com.h2database" % "h2" % "1.4.192",
    "org.slf4j" % "slf4j-nop" % "1.7.21"
  ) map (_ % "test")
}
