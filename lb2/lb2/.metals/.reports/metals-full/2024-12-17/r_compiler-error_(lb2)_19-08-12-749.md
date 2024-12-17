file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala
### dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Success) is not assigned

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1112
uri: file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala
text:
```scala
import scala.util.{Try, Success, Failure}
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
  val problem = c1.isSuccess && c1.get &&
              c2.isSuccess && c2.get &&
              c3.isSuccess && c3.get &&
              c4.isSuccess && c4.get
  problem match{
    case Success[@@]
  }
}

@main def main = {
  println(goodEnoughPassword("asdF"))
}
```



#### Error stacktrace:

```
dotty.tools.dotc.ast.Trees$Tree.tpe(Trees.scala:74)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:208)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:104)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:47)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:409)
```
#### Short summary: 

dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Success) is not assigned