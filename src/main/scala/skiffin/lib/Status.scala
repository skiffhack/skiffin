package skiffin.lib

import net.liftweb._
import http._
import util._
import common._
import actor._
import Helpers._

import java.util.Date

case class Person(email: String, in: Boolean = false, when: Date = new Date)


object Status extends LiftActor {

 
  var people: List[Person] = Person("richard@dallaway.com") :: Person("tom") :: Nil
 
  override def messageHandler = {
    case p: Person =>
      people = p :: people
  }
  
  
   
}