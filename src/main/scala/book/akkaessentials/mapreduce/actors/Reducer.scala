package book.akkaessentials.mapreduce.actors

import java.util.HashMap
import akka.actor.{ Actor, ActorRef }
import book.akkaessentials.mapreduce.messages.{ MapData, ReduceData, Word }

class Reducer(agregator: ActorRef) extends Actor {

  def receive = {
    case message: MapData =>
      agregator ! reduce(message.dataList)
  }

  def reduce(words: List[Word]): ReduceData = {
    val reducedMap = new HashMap[String, Integer]()
    
    for (word <- words) {
      if (reducedMap.containsKey(word.value)) {
        reducedMap.put(word.value, reducedMap.get(word.value) + 1)
      } else {
        reducedMap.put(word.value, 1)
      }
    }
    
    ReduceData(reducedMap)
  }
}