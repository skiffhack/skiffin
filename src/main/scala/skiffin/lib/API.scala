package skiffin.lib

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.common._
import net.liftweb.util.Helpers._

import net.liftweb.http.LiftResponse
import net.liftweb.http.OkResponse

object API extends RestHelper {

  serve {
    
    case "api" :: "verify" :: assertion :: Nil JsonGet _ => 
      val email = BrowserId.verify(assertion)
      println("GOT "+email)
      OkResponse()
    
    
  }
  
}