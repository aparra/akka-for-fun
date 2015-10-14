package book.akkaessentials.mapreduce.messages

import java.util.Map
import scala.collection.mutable.ArrayBuffer

case class Word(value: String, count: Integer = 1)

case class Result()

case class MapData(dataList: List[Word])

case class ReduceData(reduceDataMap: Map[String, Integer])
