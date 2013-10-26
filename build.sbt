name := "scalmon"

version := "0.0.0"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases",
  "sonatype-public" at "https://oss.sonatype.org/content/groups/public"
)

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.1.0"
  //"org.scalatest" % "scalatest_2.10" % "1.9.1",
  //"play" % "play_2.10" % "2.1-SNAPSHOT"
)
