name := "MinTemperaturesDataset"

version := "1.0"

organization := "com.sundogsoftware"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "3.0.2" % "provided",
"org.apache.spark" %% "spark-sql" % "3.0.2" % "provided"
)
