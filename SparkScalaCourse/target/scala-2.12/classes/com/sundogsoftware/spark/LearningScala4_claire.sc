// Data Structures

// Tuples (common in scala)
// Immutable lists

val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")

println(captainStuff)

// Refer to the individual fielts with a ONE-BASED index
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)

// can create key value pairs
val picardsShip = "Picard" -> "Enterprise-D"
println(picardsShip._2)
val aBunchofStuff = ("Kirk", 1964, true) // tuple can contain different data types

// lists
// more functionality than a tuple
// must be of same type
val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")

println(shipList(1))
// 0 based in lists but 1 based in tupe

println(shipList.head)
println(shipList.tail) // everything apart from first item

for (ship <- shipList) {println(ship)}

// map some function to everything in the list
val backwardsShips = shipList.map( (ship: String) => {ship.reverse})
for (ship <- backwardsShips) {println(ship)}

// reduce () to combine all the elements together using some function

val numberList = List(1,2,3,4,5)
val sum = numberList.reduce( (x:Int, y:Int) => x+y)
println(sum)
// reduce can collapse all the results into a final answer
// map and reduce are very parallelizable

// filter
val iHateFives = numberList.filter( (x:Int) => x != 5)
// cleaning data in a way that is parallelisable

val iHateThrees = numberList.filter(_ != 3)

// Concatenate lists
val moreNumbers = List(6,7,8)
val lotsOfNumbers = numberList ++ moreNumbers

val reversed = numberList.reverse
val sorted = reversed.sorted
val lotsOfDuplicates = numberList ++ numberList
val distictValues = lotsOfDuplicates.distinct
val maxValue = numberList.max
val total = numberList.sum

val hasThree = iHateThrees.contains(3)

// MAPS (dictionaries or key lookups)
val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
println(shipMap("Janeway"))
println(shipMap.contains("Archer"))

// one way of handling missing values
val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
println(archersShip)

// activity

val numbers = (1 to 20).toList

def isDivisibleByThree(x: Int) : Boolean = {
  val remainder: Int = x % 3
  if (remainder == 0) {true}
  else {false}
}

isDivisibleByThree(2)

for (x <- numbers) {if (isDivisibleByThree(x)) {println(x)}}

val threeBool = numbers.filter( (x:Int) => isDivisibleByThree(x))
