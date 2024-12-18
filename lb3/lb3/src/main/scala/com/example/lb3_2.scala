import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorSystem
//Актор, суммирующий значения
object AddingActor:

    type Addable = Int | Double | String
    case class AddMessage(a:Addable, b:Addable, replyTo:ActorRef[Addable])
    def apply[A]():Behavior[AddMessage] = Behaviors.receive{
        (context, message)=>
            message.replyTo ! {(message.a, message.b) match
                case (a:String, b:Addable) => a + b
                case (a:Addable, b:String) => b.prependedAll(a.toString())
                case (a:Int, b:Int) => a + b
                case (a:Double, b:Double) => a + b
                case (a:Int, b:Double) => a+b
                case (a:Double, b:Int) => a+b
            }
        Behaviors.same
    }

object AddingClient{

    import AddingActor.Addable
    import AddingActor.AddMessage

    def apply(server: ActorRef[AddingActor.AddMessage]): Behavior[Addable] = Behaviors.setup { context =>
        server ! AddMessage(2, 2, context.self)
        server ! AddMessage(1.5, 1.5, context.self)
        server ! AddMessage("Hello, ", "World!", context.self)

        Behaviors.receiveMessage { result =>
            context.log.info(s"Received sum result: $result")
            Behaviors.same
        }
    }
}

object AddingSystem{
    import AddingActor.AddMessage
    
    def apply(): Behavior[AddMessage] = Behaviors.setup { context =>
        val server = context.spawn(AddingActor(), "server")
        val client = context.spawn(AddingClient(server), "client")
        val client2 = context.spawn(AddingClient(server), "client2")
        Behaviors.same
    }
}

@main def AddingMain():Unit =
    val system = ActorSystem(AddingSystem(),"system")