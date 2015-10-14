package book.akkaessentials.mapreduce.actors

import akka.actor.{ Actor, ActorRef }
import book.akkaessentials.mapreduce.messages.{ MapData, Word }

class Mapper(reducer: ActorRef) extends Actor {

  val IGNORE_WORDS = List("a", "am", "an", "and", "are", "as", "at", "be", "do", "go", "if", "in", "is", "it", "of", "on", "the", "to")

  def receive = {
    case message: String =>
      reducer ! evaluateExpression(message) 
  }
  
  def evaluateExpression(message: String): MapData = {
    val words = message.split(" ").filterNot { IGNORE_WORDS.contains(_) }.map { value => Word(value.toLowerCase) }
    MapData(words.toList)
  }
}