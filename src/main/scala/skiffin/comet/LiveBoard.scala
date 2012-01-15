package skiffin.comet

import net.liftweb.common._
import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import net.liftweb._
import scala.xml._
import skiffin.lib._

/**
 * The screen area that represents the complete
 * state of the UI for the list of people, and
 * updates as people change state.
 */
class LiveBoard extends CometActor with CometListener {

  private var people: List[Person] = Nil
  
  // The actor that sends us changes
  def registerWith = Status
  
  override def lowPriority = {
    
    // For now we just pass around the whole list
    // and re-render.
    case ps: List[Person] => 
      people = ps
      reRender()
      
    // TODO: implement receiving a single Person
    // and send JavaScript command to update
    // change, addition or removal
      
    case x => println("LiveBoard Unexpected msg "+x)  
  }
  
  def render = "li *" #> people.map( p => 
    	".list-email" #> p.email & 
    	".list-status" #> button(p)
		  ) &  ClearClearable
  
  private def button(p: Person) = p.in match {
    case false => <input disabled="disabled" type="checkbox"></input>
    case true => <input checked="checked" disabled="disabled" type="checkbox"></input>
  }
      
  // In theory checked={checked(p)} should work in place of the match above.
  //private def checked(in: Boolean) = if (in) Some(Text("checked")) else None
  
		  
}