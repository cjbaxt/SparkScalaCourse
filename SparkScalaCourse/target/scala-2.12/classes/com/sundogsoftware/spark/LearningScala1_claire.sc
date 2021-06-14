   // VALUES are immutable constants (we cannot change it)
   val hello: String = "Hola!"

   // VARIABLES are mutable (can change them after you define them)
   var helloThere: String = hello
   helloThere = hello + " There!"
   println(helloThere)

   // how to get around the immutable constants
   val immutableHelloThere = hello + " There"
   println(immutableHelloThere)

   // Data Types
   val numberOne: Int = 1
   val truth: Boolean = true
   val letterA: Char = 'a'
   val pi: Double = 3.14159264
   val piSinglePrecision: Float = 3.14159264f
   val bigNumber: Long = 123456789
   val smallNumber: Byte = 127 // -127 to +127  or 0-265 (if usigned)

   println("Here is a mess: " + numberOne + truth + letterA + pi + bigNumber)

   println(f"Pi is about $piSinglePrecision%.3f")
   println(f"Zero padding on the left: $numberOne%05d") //useful for aligning output in columns
   println(s"I can use the s prefix to use variables like $numberOne $truth $letterA")

   println(s"The s prefix isn't limited to variables, I can include any expression. Like ${1+2}")

   val theUltimateAnswer: String = "To life, the universe, and everything is 42"
   val pattern = """.* ([\d]+).*""".r
   val pattern(answerString) = theUltimateAnswer
   val answer = answerString.toInt
   println(answer)

   // Booleans
   val isGreater = 1 > 2
   val isLesser = 1 < 2
   val impossible = isGreater & isLesser // bitwise and
   val anotherWay = isGreater && isLesser // logical and
   // || is or

   val picard: String = "Picard"
   val bestCaptain: String = "Picard"
   val isBest: Boolean = picard == bestCaptain // double = goes inside the string

   println(f"Pi multiplied by 2 is: ${pi*2}%.3f")