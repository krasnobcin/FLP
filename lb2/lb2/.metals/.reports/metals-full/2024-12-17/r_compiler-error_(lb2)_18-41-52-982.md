file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala
### java.lang.IndexOutOfBoundsException: -1

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 650
uri: file://<HOME>/Documents/%D0%A4%D0%9B%D0%9F/lb2/lb2/src/main/scala/Main.scala
text:
```scala
import scala.util

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
  if (password.length()>=8)
    return Either[@@]"Password is too short"
}

@main def main = {
  println(goodEnoughPassword("asdF"))
}
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:129)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:244)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:104)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:47)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:409)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: -1