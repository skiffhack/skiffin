package skiffin.lib

import net.liftweb._
import http._
import util._
import common._
import actor._
import Helpers._

import java.util.Date

case class Person(email: String, in: Boolean = false, when: Date = new Date)


object Status extends LiftActor with ListenerManager {

 
  var people: List[Person] = Person("richard@dallaway.com") :: Person("Tom") :: Nil
 
  // What to send to LiveBoard when that is created
  def createUpdate = people 
  
  
  override def lowPriority = {
    case p: Person =>
      people = p :: people.filterNot(_.email == p.email) 
      updateListeners()
      
    case x => println("Status Unexpected msg "+x)  
  }
  	
  
  
   
}