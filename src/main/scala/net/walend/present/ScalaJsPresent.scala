package net.walend.present

import net.walend.shipit2016b.AboutKapow
import org.scalajs.dom._
import org.scalajs.dom.html.{Element, _}
import org.scalajs.jquery.{JQuery, _}

import scala.scalajs.js.JSApp
import scalatags.JsDom.TypedTag

/**
  * Main front page of ScalaJsPresent
  *
  * @author dwalend
  * @since 10-20-2016
  */
object ScalaJsPresent extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  val slides = AboutKapow.slides
  var currentSlideIndex = 0

  def setupUI(): Unit = {

    toSlide(currentSlideIndex)

    val body: JQuery = jQuery("body")

    body.keyup(keyPressed _)
  }

  def keyPressed(event:JQueryEventObject) = {
    val BACKWARDS_KEY = 37
    val FORWARDS_KEY = 39

    val index = if(event.which == BACKWARDS_KEY) currentSlideIndex - 1
                        else if(event.which == FORWARDS_KEY) currentSlideIndex + 1
                        else currentSlideIndex
    toSlide(index)
  }

  def safeSlideIndex(index:Int):Int = {
    if(index < 0) 0
    else if (index >= slides.length) slides.length -1
    else index
  }

  def toSlide(i:Int) = {
    currentSlideIndex = safeSlideIndex(i)

    val head: JQuery = jQuery("head")
    head.html(
      s"""
         |    <meta charset="UTF-8">
         |    <title>${slides(currentSlideIndex).name}</title>
         |    <link rel="stylesheet" href="./target/scala-2.11/classes/styles/moonDesign.css">
         |    <link rel="stylesheet" href="./target/scala-2.11/classes/highlight/styles/dracula.css">
       """.stripMargin)

    val body: JQuery = jQuery("body")
    body.addClass("container")

    val slideTags: TypedTag[Div] = ToScalaTags.toSlideHtml(slides(currentSlideIndex))
    body.html(slideTags.toString())
  }

}