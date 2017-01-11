scalaVersion := "2.12.0"

name := "autocomplete"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.8.6" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")
