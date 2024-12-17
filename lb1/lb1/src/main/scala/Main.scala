import scala.math._

def hello(n: Int) ={
  for i <- (0 until n) do
    val x = n-i
    if (i%2==0) then
      println(s"Hello $i")
    else 
      println(s"Hello $x")
}

def transformSequence(s: Seq[Int]):(Seq[Int],Seq[Int]) = {
  val s1 = s.zip(0 until s.length).filter(_._2%2==0).map((i:(Int,Int))=> i._1)
  val s2 = s.zip(0 until s.length).filter(_._2%2==1).map((i:(Int,Int))=> i._1)
  return (s1,s2)
}

def findMax(s: Seq[Int]):Int={
  val s1 = s.reduce((a: Int,b: Int) => if a>b then a else b)
  return s1
}

def patternMatching(x: Int) = {
  x match
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
}

def compose(f: Int=>Int, g: Int=>Double): Int=>Double={
  return (x:Int) => g(f(x))
}

@main def main() = {
  println("Task 2")
  hello(5)
  println("Task 3")
  println(transformSequence((0 until 10)))
  val a = Seq(12,234,2,35,234)
  println(findMax(a))
  println("Task 4")
  patternMatching(2)
  println("Task 5")
  println(compose((a:Int)=>a*a*a,(a:Int)=>sqrt(a))(4))
}