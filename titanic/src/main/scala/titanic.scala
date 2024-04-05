import com.github.tototoshi.csv.CSVReader
import java.io.File
import com.github.tototoshi.csv.defaultCSVFormat


object Titanic  extends App{
  val file: File = new File("src/main/resources/titanic.csv")

  def parse(file: File): List[Passenger] = {



    try {
      val reader: CSVReader = CSVReader.open(file)

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

  val passengers: titanicClass = new titanicClass(parse(file))
  val women: titanicClass = new titanicClass(passengers.byGender("female"))
  val men: titanicClass = new titanicClass(passengers.byGender("male"))
  val menSurvive: titanicClass = new titanicClass(men.survive())
  val womenSurvive: titanicClass = new titanicClass(women.survive())
  val adults: titanicClass = new titanicClass(passengers.byAge(18, 100))
  val firstClass: titanicClass = new titanicClass(passengers.byCabinClass(1))
  val thirdClass: titanicClass = new titanicClass(passengers.byCabinClass(3))
  val firstClassSurvive: titanicClass = new titanicClass(firstClass.survive())
  val thirdClassSurvive: titanicClass = new titanicClass(thirdClass.survive())
  val children: titanicClass = new titanicClass(passengers.byAge(0, 18))
  val childrenFirstClass: titanicClass = new titanicClass(children.byCabinClass(1))
  val childrenThirdClass: titanicClass = new titanicClass(children.byCabinClass(3))
  println(thirdClassSurvive.len().toFloat / thirdClass.len().toFloat * 100 + " %")
  println(firstClassSurvive.len().toFloat / firstClass.len().toFloat * 100 + " %")
  println(childrenThirdClass.len())
}
