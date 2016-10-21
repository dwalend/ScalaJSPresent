package tutorial.webapp

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.Node
import org.scalajs.dom.document
import org.scalajs.dom.html.Heading
import org.scalajs.jquery.{JQuery, jQuery}

import scalatags.JsDom.TypedTag
      /*
object TutorialApp extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  def setupUI(): Unit = {
    jQuery("""<button type="button">Click me!</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))
    jQuery("body").append("<p>Hello World</p>")
  }

  def appendPar(targetNode: Node, text: String): Unit = {
    import scalatags.JsDom.all._

    val body: JQuery = jQuery("body")

//    jQuery("body").append(s"<p>$text</p>")

    val header: TypedTag[Heading] = h1(s"$text")
    //val renderedHeader: Heading = header.render

    body.html(header.toString())
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

}
*/
