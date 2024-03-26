class titanicClass (passengers: List[Passenger]) {
  def avgAge(): Int = passengers.map(_.age).sum / passengers.length
  def byGender(gender: String): List[Passenger] = passengers.filter(x => x.sex == gender)
  def survive(): List[Passenger] = passengers.filter(x => x.survive)
  def byAge(ageOver: Int, ageUnder: Int): List[Passenger] = passengers.filter(x => x.age <= ageUnder && x.age > ageOver)
  def byCabinClass(cabinClass: Int): List[Passenger] = passengers.filter(x => x.cabinClass == cabinClass)
  
  def len(): Int = passengers.length
  def names(): List[String] = passengers.map(x => x.name)
}
