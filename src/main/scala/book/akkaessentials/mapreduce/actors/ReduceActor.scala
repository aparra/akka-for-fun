package book.akkaessentials.mapreduce.actors

import akka.actor.{Actor, ActorRef}
import book.akkaessentials.mapreduce._
import book.akkaessentials.mapreduce.messages.{MapData, ReduceData, WordCount}

import scala.collection.mutable

class ReduceActor(aggregateActor: ActorRef) extends Actor {

  def receive = {
    case message: MapData =>
      aggregateActor ! reduce(message.dataList)
  }

  private def reduce(words: List[WordCount]): ReduceData = {
    val wordsCount = mutable.Map[String, Integer]()

    for (word <- words) {
      if (wordsCount.contains(word.value)) {
        val count = wordsCount(word.value) + defaultCount
        wordsCount += word.value -> count
      } else {
        wordsCount += word.value -> defaultCount
      }
    }

    ReduceData(wordsCount.toMap)
  }
}
