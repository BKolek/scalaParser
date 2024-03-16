object titanic extends App {
  case class Passenger(id: String, name: String, survive: String)
  class Passengers(Seq[Passenger])

}
