package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._

object SparkSQLDataset {

  // A compact way of defining an object, used for constructing database,
  // defining the schema for our data.
  // with datasets we need to have done this at compile time
  case class Person(id:Int, name:String, age:Int, friends:Int)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Use SparkSession interface
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // Load each line of the source data into an Dataset
    import spark.implicits._
    val schemaPeople = spark.read
      .option("header", "true") // this matches the case class from above
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person] // takes csv dataframe and converts it to dataset, if you comment this out then you will lose the compile time checks but it will still work

    schemaPeople.printSchema() // schema people dataset
    
    schemaPeople.createOrReplaceTempView("people")

    val teenagers = spark.sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")
    
    val results = teenagers.collect()
    
    results.foreach(println)
    
    spark.stop() // important to stop the soark session
  }
}