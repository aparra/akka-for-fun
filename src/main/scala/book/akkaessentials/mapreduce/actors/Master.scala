package book.akkaessentials.mapreduce.actors

import akka.actor.{ Actor, Props }
import book.akkaessentials.mapreduce.messages.Result

class Master extends Actor {

  val aggregator = context.actorOf(Props[Aggregator], name = "aggregator")
  val reducer = context.actorOf(Props(new Reducer(aggregator)), name = "reducer")
  val mapper = context.actorOf(Props(new Mapper(reducer)), name = "mapper")

  def receive = {
    case message: String =>
      mapper ! message
    case message: Result =>
      aggregator ! message
  }
}