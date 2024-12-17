import scala.util.{Try, Success, Failure}
import scala.concurrent.Future
import scala.io.StdIn
import scala.util.control.Breaks._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

def integral(f:Double=>Double,l:Double,r:Double,count:Int):Double={
    var step = (r-l)/count
    (0 until count)
        .map(i=>l+step*i)
        .map(i=>f(i)+f(i+step))
        .reduce(_+_)*step/2
}

def goodEnoughPassword(password:String): Boolean = {
  return password.length()>=8 & 
    password.collect[Boolean]((x:Char)=>(x>='a' & x<='a')).reduce(_ | _) &
    password.collect[Boolean]((x:Char)=>(x>='A' & x<='Z')).reduce(_ | _) &
    password.collect[Boolean]((x:Char)=>(x>='0' & x<='9')).reduce(_ | _)
}

def goodEnoughPassword2(password:String):Either[Boolean, String] = {
  val c1 = Try(password.length()>=8)
  val c2 = Try(password.collect[Boolean]((x:Char)=>(x>='a' & x<='a')).reduce(_ | _))
  val c3 = Try(password.collect[Boolean]((x:Char)=>(x>='A' & x<='Z')).reduce(_ | _))
  val c4 = Try(password.collect[Boolean]((x:Char)=>(x>='0' & x<='9')).reduce(_ | _))
  val problem = Try(c1.isSuccess && c1.get &&
              c2.isSuccess && c2.get &&
              c3.isSuccess && c3.get &&
              c4.isSuccess && c4.get)
  problem match{
    case Success(v) => 
        if (!c1.get) return Right[Boolean,String]("Password too short")
        else if (!c2.get) return Right[Boolean,String]("Password must have at least one lowercase letter")
        else if (!c3.get) return Right[Boolean,String]("Password must have at least one uppercase letter")
        else if (!c4.get) return Right[Boolean,String]("Password must have at least one number")
        else return Left[Boolean,String](true)
    case Failure(e)=>
        return Left[Boolean,String](false)
  }
}

def tryPassword():String = {
  while(true){
    val password = StdIn.readLine("Enter a password\n")
    val res = goodEnoughPassword2(password)
    if res.isLeft then
      if res.left.get==true then
      return password
    println(res.right.get)
  }
    return "123"
}

def readPassword():Future[String] = {
  return Future[String](tryPassword())
}

@main def main = {
  println("Taks1")
  println(integral(_*2,0,1,10000))
  println("Task2")
  println(goodEnoughPassword("adsaf12"))
  println("Task3")
  println(goodEnoughPassword2("asdFD12423"))
  println("Task4")
  val res = readPassword();
  println(res)
}