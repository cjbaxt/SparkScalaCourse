// Flow control

// If / else:
if (1 > 3) println("Impossible!") else println("The world makes sense.")

if (1 > 3) {
  println("Impossible!")
  println("Really?")
} else {
  println("The world makes sense.")
  println("still.")
}

// Matching
val number = 3
number match {
  case 1 => println("One")
  case 2 => println("Two")
  case 3 => println("Three")
  case _ => println("Something else")
}

for (x <- 1 to 4) { //x less than dash
  val squared = x * x
  println(squared)
}

var x = 10 //mutable variable, not good practice in scala
while (x >= 0) { //while greater than or equal to zero
  println(x)
  x -= 1
}

x = 0 //do while loop
do { println(x); x+=1 } while (x <= 10)

// Expressions

{val x = 10; x + 20}

println({val x = 10; x + 20})

// EXERCISE
// Write some code that prints out the first 10 values of the Fibonacci sequence.
// This is the sequence where every number is the sum of the two numbers before it.
// So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

def fib(n:Int): Int= {if(n<=1) return n;  return  fib(n-1)+fib(n-2);}
for(i<- 0 to 9){ println(fib(i))}