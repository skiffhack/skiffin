package skiffin.lib

import dispatch._
import liftjson.Js._
import net.liftweb.json._
import JsonParser._

import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.http.S

import net.liftweb.common._

object BrowserId extends Loggable {

  val site = "http://"+S.hostName+":8080"
  
  type Email = String
  
  def verify(assertion: String): Option[Email] = {
    
    val args = Map( "assertion" -> assertion, "audience" -> site)
    
    val u = url("https://browserid.org/verify") <<? args POST
    
    val http = new Http()

    http(u ># { json => 
      for( JString(email) <- json \ "email") yield email
    }) headOption
    
  }
  
}