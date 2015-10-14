package book.akkaessentials.mapreduce.actors

import java.util.{ HashMap, Map }
import scala.collection.JavaConversions.asScalaSet
import akka.actor.Actor
import book.akkaessentials.mapreduce.messages.{ ReduceData, Result }

class Aggregator extends Actor {

  var finalReducedMap = new HashMap[String, Integer]

  def receive: Receive = {
    case message: ReduceData =>
      aggregateInMemoryReduce(message.reduceDataMap)
    case message: Result =>
      println(finalReducedMap)
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Integer]) {
    reducedList.keySet().foreach { key => 
      if (finalReducedMap.containsKey(key)) {
        var count = reducedList.get(key)
        count += finalReducedMap.get(key)
        finalReducedMap put (key, count)
      } else {
        finalReducedMap put (key, reducedList.get(key))
      }
    }
  }
}