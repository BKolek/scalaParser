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
  // mezczyzni i kobiety odfiltrowani
  val women: titanicClass = new titanicClass(passengers.byGender("female"))
  val men: titanicClass = new titanicClass(passengers.byGender("male"))
  // ci ktorzy przezyli
  val menSurvive: titanicClass = new titanicClass(men.survive())
  val womenSurvive: titanicClass = new titanicClass(women.survive())
  // tylko dorosli
  val adults: titanicClass = new titanicClass(passengers.byAge(18, 100))
  // podzial na klasy
  val firstClass: titanicClass = new titanicClass(passengers.byCabinClass(1))
  val thirdClass: titanicClass = new titanicClass(passengers.byCabinClass(3))
  // podzial na klasy tych co przezyli
  val firstClassSurvive: titanicClass = new titanicClass(firstClass.survive())
  val thirdClassSurvive: titanicClass = new titanicClass(thirdClass.survive())
  // tylko ludzie ktorzy byli starsi niz 69 lat i przezyli oraz byli posiadaczami biletu pierwszej klasy
  val elderlySurvivedFirstClass: titanicClass = new titanicClass(firstClass.byAge(70, 100))
  // mozna zrobic duzo wiecej kombinacji
  

  println(thirdClassSurvive.len().toFloat / thirdClass.len().toFloat * 100 + " %")
  println(firstClassSurvive.len().toFloat / firstClass.len().toFloat * 100 + " %")

}
