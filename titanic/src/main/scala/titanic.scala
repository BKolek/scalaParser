import com.github.tototoshi.csv.CSVReader
import java.io.File
import com.github.tototoshi.csv.defaultCSVFormat
case class Passenger(id: Int, survive: Boolean, cabinClass: Int)

object Titanic  extends App{
  val file = new File("/home/bartosz/bigdata/Parser/parser/src/main/resources/titanic.csv")

  def parse(file: File): List[Passenger] = {

    def survive(binary: String): Boolean = {
      (binary == "1")

    }
    try {
      val reader = CSVReader.open(file)

      reader.readNext()
      val collectionOfPassengers: List[Passenger] = reader
        .toStream
        .map(fields => Passenger(fields.head.toInt,
          survive(fields(1)),
          fields(2).toInt,
        ))
        .toList

      reader.close()
      collectionOfPassengers
    } catch {
      case e: Exception =>
        println(s"Error occurred: ${e.getMessage}")
        List.empty[Passenger]
    }
  }

  val passengers = parse(file)
  passengers.foreach(println)
}
