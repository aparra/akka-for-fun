package book.akkaessentials.mapreduce.messages

sealed trait MapReduceMessage

case class WordCount(value: String, count: Integer) extends MapReduceMessage

case class MapData(dataList: List[WordCount]) extends MapReduceMessage

case class ReduceData(reduceDataMap: Map[String, Integer]) extends MapReduceMessage

case class Result() extends MapReduceMessage
