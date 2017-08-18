package book.akkaessentials.mapreduce.actors

import akka.actor.{Actor, ActorRef}
import book.akkaessentials.mapreduce._
import book.akkaessentials.mapreduce.messages.{MapData, WordCount}

class MapActor(reduceActor: ActorRef) extends Actor {

  def receive = {
    case message: String =>
      reduceActor ! evaluateExpression(message)
  }

  def evaluateExpression(message: String): MapData = {
    val words = message.split(" ").map { word =>
      WordCount(word.toLowerCase, count = defaultCount)
    }
    MapData(words.toList)
  }
}
