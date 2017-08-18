package book.akkaessentials.mapreduce.actors

import akka.actor.Actor
import book.akkaessentials.mapreduce.messages.{ReduceData, Result}

import scala.collection.mutable

class AggregateActor extends Actor {

  private val wordCount = mutable.Map[String, Integer]()

  def receive: Receive = {
    case message: ReduceData =>
      aggregateInMemoryReduce(message.reduceDataMap)
    case _: Result =>
      println(wordCount)
  }

  private def aggregateInMemoryReduce(reducedList: Map[String, Integer]) {
    reducedList.keys.foreach { key =>
      if (wordCount.contains(key)) {
        val count = reducedList(key) + wordCount(key)
        wordCount += key -> count
      } else {
        wordCount += key -> reducedList(key)
      }
    }
  }
}
