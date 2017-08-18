package book.akkaessentials.mapreduce.actors

import akka.actor.{Actor, Props}
import book.akkaessentials.mapreduce.messages.Result

class MasterActor extends Actor {

  private val aggregateActor = context.actorOf(Props[AggregateActor], name = "aggregator")
  private val reduceActor    = context.actorOf(Props(new ReduceActor(aggregateActor)), name = "reducer")
  private val mapActor       = context.actorOf(Props(new MapActor(reduceActor)), name = "mapper")

  def receive = {
    case message: String =>
      mapActor ! message
    case message: Result =>
      aggregateActor ! message
  }
}
