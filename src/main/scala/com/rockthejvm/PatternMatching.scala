package com.rockthejvm

object PatternMatching extends App {
  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  println(order)

  // Case class decomposition
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43)   // Person.apply("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name $n and I am $a years old"
    case _ => "Something else"
  }
  println(personGreeting)

  // deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't know what you're talking about"
  }
  println(bandDescription)

  // decomposing lists
  // if pattern matching doesn't match anything, it will throw MatchError
  // PM will try all cases in sequence
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing on its 2nd position"
    case _ => "unknown list"
  }
  println(listDescription)

  
}
