val sparkVersion: String = "2.4.0"
val sparkTestBaseVersion: String = "0.11.0"

lazy val commonSettings = Seq(
  name := "spark-test-base-demo",
  organization := "pairwiseltd",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    libraryDependencies ++=
      Seq("com.holdenkarau" %% "spark-testing-base" % s"${sparkVersion}_$sparkTestBaseVersion" % "test",
        "org.apache.spark" %% "spark-core" % sparkVersion,
        "org.apache.spark" %% "spark-sql" % sparkVersion,
        "org.apache.spark" %% "spark-hive" % sparkVersion,
        "org.apache.spark" %% "spark-mllib" % sparkVersion)
  )
