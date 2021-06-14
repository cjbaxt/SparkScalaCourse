package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

/** Compute the total amount spent per customer in some fake e-commerce data. */
object TotalSpentByCustomer_claire {

  /** A function that splits a line of input into (age, numFriends) tuples. */
  def parseLine(line: String): (Int, Int) = {
    // Split by commas
    val fields = line.split(",")
    // Extract the age and numFriends fields, and convert to integers
    val id = fields(1).toInt
    val amount = fields(3).toInt
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


    val totalsByCustomer = rdd.mapValues(x => (x, 1)).reduceByKey( (x,y) => (x._1 + y._1, x._2 + y._2))

    // Collect the results from the RDD (This kicks off computing the DAG and actually executes the job)
    val results = totalsByCustomer.collect()

    // Sort and print the final results.
    results.sorted.foreach(println)

  }

}


