package com.rockthejvm

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App {
  /**
  // lazy evaluation
  **/
  lazy val aLazyValue = 2
  // This will not print because lazy value gets yet evaluated
  // Useful in infinite collections
  lazy val lazyValueWithSideEffect = {
    println("I'm so very lazy !")
    43
  }

  // This will print because lazy value gets evaluated
  val eagerValue = lazyValueWithSideEffect + 1

  /**
  // "pseudo-collections": Option, Try
  **/
  def methodWhichCanReturnNull(): String = "hello, Scala"
  val anOption = Option(methodWhichCanReturnNull())   // Some("hello, Scala)
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }
  println(anOption)

  def methodWhichCanThrowException(): String = throw new RuntimeException
  // Classic try
  try {
    methodWhichCanThrowException()
  } catch {
    case e: Exception => "defend against this evil exception"
  }
  // Try pseudo-collection
  val aTry = Try(methodWhichCanThrowException())
  // a Try = "collection" with either a value if the code went well, or an exception if the code threw one
  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }

  /**
    * Evaluate something on another thread
    * (asynchronous programming
    */
  val aFuture = Future {      // Future.apply
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value")
    67
  }
  // future is a "collection" which contains a value when it's evaluated
  // future us composable with map, flatMap and filter

  // To let the Future finished before the main thread ends
  Thread.sleep(2000)

  /**
    * Implicits basics
    */
  // #1: Implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs)    // aMethodWithImplicitArg(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }
  println(23.isEven())  // new MyRichInteger(23).isEven()
  // use this carefully
}
