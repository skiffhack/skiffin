package skiffin.lib

import net.liftweb._
import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.common._
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

import java.util.Date

object API extends RestHelper with Loggable {

  def jsonPerson(p: Person): JObject = ("email" -> p.email) ~
     	("in" -> p.in) ~ ("when" -> p.when.getTime)
  
  serve {
    
    case "api" :: "v1" :: "people" :: Nil JsonGet _ =>
       ("count" -> Status.people.size) ~
       ("people" -> Status.people.map(jsonPerson) ) : JObject

    case Req("api" :: "v1" :: email :: Nil, suffix, GetRequest) =>
      val address = "%s.%s" format (email,suffix) // doh!
      Status.people.find(_.email == address).map(jsonPerson)
     	  
    case req @ Req("api" :: "v1" :: email :: Nil, suffix, PostRequest) =>
      val address = "%s.%s" format (email,suffix)
      setStatus(address, req.json).map(jsonPerson)
     
      
    case "api" :: "v1" :: "login" :: assertion :: Nil JsonGet _ => 
      val person = for { email <- BrowserId.verify(assertion)
    		  			person <- Status.people.find(_.email == email) } yield {
        logger.info("Login from "+email)
        person
      }
      
      person.map(jsonPerson)
      
    
  }
  
  def findOrCreate(address: String): Person = 
    Status.people.find(_.email == address) getOrElse Person(email=address)  
  
    
  // Set the status, returning new person or Empty if missing JSON data
  def setStatus(address: String, jsonreq: Box[JValue]): Box[Person] = for { 
	  json <- jsonreq    
      JBool(status) = (json \ "in")
      } yield {
    	  val np = findOrCreate(address).copy(in=status,when=new Date)
    	  Status ! np
    	  np
   }
    
 
   
      
}