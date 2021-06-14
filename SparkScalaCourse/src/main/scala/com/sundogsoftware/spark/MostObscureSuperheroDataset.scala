package com.sundogsoftware.spark

import com.sundogsoftware.spark.PopularMoviesNicerDataset.loadMovieNames
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

import scala.io.{Codec, Source}

/** Find the superhero with the most co-appearances. */
object MostObscureSuperheroDataset {

  case class SuperHeroNames(id: Int, name: String)
  case class SuperHero(value: String)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("MostPopularSuperhero")
      .master("local[*]")
      .getOrCreate()

    // Create schema when reading Marvel-names.txt
    val superHeroNamesSchema = new StructType()
      .add("id", IntegerType, nullable = true)
      .add("name", StringType, nullable = true)

    // Build up a hero ID -> name Dataset
    import spark.implicits._
    val names = spark.read
      .schema(superHeroNamesSchema)
      .option("sep", " ")
      .csv("data/Marvel-names.txt")
      .as[SuperHeroNames]

    val lines = spark.read
      .text("data/Marvel-graph.txt")
      .as[SuperHero]

    val connections = lines
      .withColumn("id", split(col("value"), " ")(0))
      .withColumn("connections", size(split(col("value"), " ")) - 1)
      .groupBy("id").agg(sum("connections").alias("connections"))

    // get the minimum connection in the dataframe
    val minConnection = connections.agg(min("connections")).first().getLong(0)

    println(minConnection)

    // get the superheros that have only one connections
    val obscureSuperheros = connections.filter($"connections" === minConnection)

    // join the names
    val obscureSuperherosNames = obscureSuperheros.join(names, usingColumn = "id")

    obscureSuperherosNames.show()


//    val mostPopular = connections
//        .sort($"connections".desc)
//        .first()
//
//    val mostPopularName = names
//      .filter($"id" === mostPopular(0))
//      .select("name")
//      .first()
//
//    println(s"${mostPopularName(0)} is the most popular superhero with ${mostPopular(1)} co-appearances.")
  }
}
