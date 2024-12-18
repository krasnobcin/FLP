//#full-example
package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.GreeterMain.SayHello

//#greeter-actor
object Greeter {
  // Сообщение для приветствия.
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  // Сообщение-ответ после приветствия.
  final case class Greeted(whom: String, from: ActorRef[Greet])

  // Поведение актора: обрабатывает сообщения Greet.
  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom) // Логирование.
    message.replyTo ! Greeted(message.whom, context.self) // Отправка ответа.
    Behaviors.same // Актор продолжает работать.
  }
}
//#greeter-actor

//#greeter-bot
object GreeterBot {
  // Начальное поведение актора, принимающего Greeted.
  def apply(max: Int): Behavior[Greeter.Greeted] = {
    bot(0, max)
  }

  // Рекурсивное поведение актора: увеличивает счетчик и отправляет новые запросы.
  private def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1 // Увеличение счетчика.
      context.log.info("Greeting {} for {}", n, message.whom) // Логирование.
      if (n == max) {
        Behaviors.stopped // Завершение работы.
      } else {
        message.from ! Greeter.Greet(message.whom, context.self) // Отправка нового запроса.
        bot(n, max) // Новое состояние актора.
      }
    }
}
//#greeter-bot

//#greeter-main
object GreeterMain {
  // Сообщение для запуска взаимодействия.
  final case class SayHello(name: String)

  // Поведение корневого актора.
  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(Greeter(), "greeter") // Создание актора Greeter.

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(GreeterBot(max = 3), message.name) // Создание GreeterBot.
        greeter ! Greeter.Greet(message.name, replyTo) // Отправка приветствия.
        Behaviors.same
      }
    }
}
//#greeter-main

//#main-class
object AkkaQuickstart extends App {
  val greeterMain: ActorSystem[GreeterMain.SayHello] = ActorSystem(GreeterMain(), "AkkaQuickStart") // Система акторов.

  greeterMain ! SayHello("Charles") // Отправка сообщения для начала приветствия.
}
//#main-class
//#full-example
