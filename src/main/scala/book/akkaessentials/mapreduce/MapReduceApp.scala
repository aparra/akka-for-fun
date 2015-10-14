package book.akkaessentials.mapreduce

import akka.actor.{ ActorSystem, Props }
import book.akkaessentials.mapreduce.actors.Master
import book.akkaessentials.mapreduce.messages.Result

object MapReduceApp {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("MapReduceApp")
    val master = system.actorOf(Props[Master], name = "master")

    master ! "The quick brown fox tried to jump over the lazy dog and fell on the dog"
    master ! "Dog is man's best friend"
    master ! "Dog and Fox belong to the same family"

    Thread.sleep(500)
    master ! Result()

    Thread.sleep(500)
    system.shutdown
  }
}