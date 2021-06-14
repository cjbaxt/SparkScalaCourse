package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TotalSpentByCustomer_claire2 {


  /** A function that splits a line of input into (age, numFriends) tuples. */
  def parseLine(line: String): (Int, Float) = {
    // Split by commas
    val fields = line.split(",")
    // Extract the age and numFriends fields, and convert to integers
    val id = fields(0).toInt
    val amount = fields(2).toFloat
    // Create a tuple that is our result.
    (id, amount)
  }

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalSpentByCustomer_claire")

    // Load each line of the source data into an RDD
    val lines = sc.textFile("data/customer-orders.csv")

    // Use our parseLines function to convert to (age, numFriends) tuples
    val rdd = lines.map(parseLine)

    // Lots going on here...
    // We are starting with an RDD of form (age, numFriends) where age is the KEY and numFriends is the VALUE
    // We use mapValues to convert each numFriends value to a tuple of (numFriends, 1)
    // Then we use reduceByKey to sum up the total numFriends and total instances for each age, by
    // adding together all the numFriends values and 1's respectively.
    val totalsByCustomer = rdd.mapValues(x => (x, 1)).reduceByKey( (x,y) => (x._1 + y._1, x._2 + y._2))

    // mine is better, it shows how many transactions they made as well :D

    // Collect the results from the RDD (This kicks off computing the DAG and actually executes the job)
    val results = totalsByCustomer.collect()

    val resultsFlipped = results.map(x => (x._2._1,x._1))

    // Sort and print the final results.
    resultsFlipped.sorted.foreach(println)

  }

}


