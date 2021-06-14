package com.sundogsoftware.spark

import com.sundogsoftware.spark.DataFramesDataset.Person
import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.sql.functions.{avg, round}

object FriendsByAgeDataset_claire {

  case class Person(id: Int, name: String, age: Int, friends: Int)

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._ // this is important for any time you have spark inferring a schema
    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person]

    people.groupBy("age").avg("friends").show()

    // Sorted:
    people.groupBy("age").avg("friends").sort("age").show()

    // Formatted more nicely:
    people.groupBy("age").agg(round(avg("friends"), 2))
      .sort("age").show()

    // With a custom column name:
    people.groupBy("age").agg(round(avg("friends"), 2)
      .alias("friends_avg")).sort("age").show()

  }

}
