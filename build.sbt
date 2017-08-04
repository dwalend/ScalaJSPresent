enablePlugins(ScalaJSPlugin)

name := "Scala.js Tutorial"

scalaVersion := "2.12.2"

scalaJSUseRhino in Global := true

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.1"

libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.6.5"

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.4.7" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

skip in packageJSDependencies := false

jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"

jsDependencies += ProvidedJS / "highlight/highlight.pack.js"

jsDependencies += RuntimeDOM

persistLauncher in Compile := true

persistLauncher in Test := false