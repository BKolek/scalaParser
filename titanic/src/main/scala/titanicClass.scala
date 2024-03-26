class titanicClass (passengers: List[Passenger]) {
  def avgAge(): Int = passengers.map(_.age).sum / passengers.length
  def byGenderNumber(gender: String): List[Passenger] = passengers.filter(x => x.sex == gender)

  def avgSurviveAge(): Int = passengers.filter(x => x.survive == false).map(_.age).sum / passengers.count(x => x.survive)
  def byAgeNumber(ageOver: Int = 0, ageUnder: Int = 1000): List[Passenger] = passengers.filter(x => x.age <= ageUnder && x.age > ageOver)



}
