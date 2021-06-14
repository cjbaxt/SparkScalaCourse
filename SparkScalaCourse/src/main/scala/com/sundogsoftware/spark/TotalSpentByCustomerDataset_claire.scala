package com.sundogsoftware.spark

import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType, DoubleType}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TotalSpentByCustomerDataset_claire {

  case class customerOrders(id: Int, unknown: Int, amount: Double)

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("TotalSpentByCustomerDataset_claire")
      .master("local[*]")
      .getOrCreate()

    val customerSchema = new StructType()
      .add("id", IntegerType, nullable = true)
      .add("unknown", IntegerType, nullable = true)
      .add("amount", DoubleType, nullable = true)

    import spark.implicits._
    val ds = spark.read
      .schema(customerSchema)
      .csv("data/customer-orders.csv")
      .as[customerOrders]

    val totalByCustomer = ds
      .groupBy("id")
      .agg(round(sum("amount"), 2))
      .alias("total")

    totalByCustomer.show(totalByCustomer.count.toInt)

  }
}