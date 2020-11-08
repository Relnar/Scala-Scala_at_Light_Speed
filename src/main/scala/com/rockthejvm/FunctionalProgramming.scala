package com.rockthejvm

object FunctionalProgramming extends App {

  class Person(name: String) {
    def apply(age: Int) = println(s"I have $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // Invoking bob as a function == bob.apply(43)

  // Scala runs on the JVM
  // Functional programming:
  // - compose functions
  // - pass functions as args
  // - return functions as results
  // Conclusion: FunctionX = Function1Arg, Function2Args, ... Function22Args
  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // simpleIncrementer.apply(23)  // 24

  // All Scala function are instances of these FunctionX types
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }
  stringConcatenator("I love", " Scala") // "I love Scale"

  // Syntax sugars
  val doubler: Function1[Int, Int] = (x: Int) => 2 * x
  val doubler2: Int => Int = (x: Int) => 2 * x
  val doubler3 = (x: Int) => 2 * x
  doubler(4) // 8
  doubler2(4) // 8
  doubler3(4) // 8
  /*
    equivalent to the much longer:

    var doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(x: Int) = 2 * x
    }
   */

  // higher-order functions: take functions as args/return functions as results
  val aMappedList = List(1, 2, 3).map(x => x + 1)
  println(aMappedList) // List(2,3,4)

  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, 2 * x))
  // Alternate syntax
  val aFlatMappedList2 = List(1, 2, 3).flatMap {
    x => List(x, 2 * x)
  }
  println(aFlatMappedList) // List(1, 2, 2, 4, 3, 6)

  // Filter even numbers
  val aFilteredList = List(1, 2, 3, 4, 5).filter(x => x % 2 == 0)
  // Alternate syntax
  val aFilteredList2 = List(1, 2, 3, 4, 5).filter(_ % 2 == 0)
  println(aFilteredList2)

  // all pairs between the numbers 1,2,3 and the letters 'a', 'b', 'c'
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs)

  // for comprehensions
  // equivalent to the map/flatmap chain above
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  println(alternativePairs)

  // Collections
  // Lists
  val aList = List(1, 2, 3, 4, 5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)

  // sequences
  val aSequence: Seq[Int] = Seq(1,2,3)    // Seq.apply(1,2,3)
  val accessedElement = aSequence(1)      // element at index 1: 2

  // vectors: fast Seq implementation
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates
  val aSet = Set(1,2,3,4,1,2,3)     // Set(1,2,3,4)
  val setHas5 = aSet.contains(5)    // false
  val anAddedSet = aSet + 5         // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3        // Set(1,2,4)

  // ranges
  val aRange = 1 to 20
  val twoByTwo = aRange.map(2 * _).toList
  println(twoByTwo)

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 1234567),
    "Jane" -> 325595      // ("Jane", 325595)
  )
  println(aPhonebook)
}
