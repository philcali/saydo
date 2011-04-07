import sbt._

class MyProject(info: ProjectInfo) 
    extends DefaultProject(info)
    with conscript.Harness {
  val scalendar = "com.github.philcali" %% "scalendar" % "0.0.2"
}
