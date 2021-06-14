// Functiona
//format def <function name>(parameter name: type:...) : return type = { }

def squareIt(x: Int) : Int = {
  x * x
}

def cubeIt(x : Int) : Int = {x*x*x}

println(squareIt(2))
println(cubeIt(3))

// functions can take other functions as parameters
def transformInt(x: Int, f: Int => Int): Int = {
  f(x)
}

val result = transformInt(2, cubeIt)
println(result)

// lambda functions, anonymous functions, function literals
transformInt(3, x => x*x*x) //defined the function in line

transformInt(10, x => x/2)

transformInt(2, x => {val y = x*2; y*y}) // can contain multiple expressions in curly brackets

def toUpper(x: String): String = {x.toUpperCase()}
toUpper("claire")

def transformString(x: String, f: String => String): String = {f(x)}
transformString("claire", toUpper)
