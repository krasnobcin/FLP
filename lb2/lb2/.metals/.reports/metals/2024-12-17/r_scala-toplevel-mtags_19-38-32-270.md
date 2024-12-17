error id: file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala:[1686..1689) in Input.VirtualFile("file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala", "import scala.util.{Try, Success, Failure}
import scala.concurrent.Future
import scala.io.StdIn

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

def 

def readPassword():Future[String] = {

}

@main def main = {
  println(goodEnoughPassword2("asdasdfasdfF"))
}")
file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/file:<HOME>/Documents/%25D0%25A4%25D0%259B%25D0%259F/lb2/lb2/src/main/scala/Main.scala
file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala:43: error: expected identifier; obtained def
def readPassword():Future[String] = {
^
#### Short summary: 

expected identifier; obtained def