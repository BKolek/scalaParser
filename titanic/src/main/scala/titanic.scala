import com.github.tototoshi.csv.CSVReader
import java.io.File
import com.github.tototoshi.csv.defaultCSVFormat


object Titanic  extends App{
  val file = new File("src/main/resources/titanic.csv")

  def parse(file: File): List[Passenger] = {



    try {
      val reader = CSVReader.open(file)

      reader.readNext()
      val collectionOfPassengers: List[Passenger] = reader
        .toStream
        .flatMap { fields =>
          try {
            Some(Passenger(
              fields.head.toInt,
              fields(1) == "1",
              fields(2).toInt,
              fields(3),
              fields(4),
              fields(5).toInt,
              fields(6).toInt,
              fields(7).toInt,
              fields(8),
              fields(9).toFloat
            ))
          } catch {
            case _: Throwable => None
          }
        }
        .toList

      reader.close()
      collectionOfPassengers
    } catch {
      case e: Exception =>
        println(s"Error occurred: ${e.getMessage}")
        List.empty[Passenger]
    }
  }

  val passengers = new titanicClass(parse(file))
  val women = new titanicClass(passengers.byGender("female"))
  val men = new titanicClass(passengers.byGender("male"))
  val menSurvive = new titanicClass(passengers.survive())
  val womenSurvive = new titanicClass(women.survive())

  println(women.len())
  println(men.names())
}
