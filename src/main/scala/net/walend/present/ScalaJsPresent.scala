package net.walend.present

import net.walend.intro2scala.IntroToScala
import org.scalajs.dom.html.Div
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

  val slides: Seq[Slide] = IntroToScala.slides
  val slideHtml: Seq[TypedTag[Div]] = slides.map(ToScalaTags.toSlideHtml)
  var currentSlideIndex = 0

  def setupUI(): Unit = {

    //todo just set the title each time
    val head: JQuery = jQuery("head")
    head.html(
      s"""
         |    <meta charset="UTF-8">
         |    <title>${slides(currentSlideIndex).name}</title>
         |    <link rel="stylesheet" href="./target/scala-2.12/classes/styles/moonDesign.css">
         |    <link rel="stylesheet" href="./target/scala-2.12/classes/highlight/styles/dracula.css">
       """.stripMargin)

    changeToSlide(currentSlideIndex)

    val body: JQuery = jQuery("body")
    body.addClass("container")
    body.keyup(keyPressed _)
  }

  def keyPressed(event:JQueryEventObject): JQuery = {
    val BACKWARDS_KEY = 37
    val FORWARDS_KEY = 39

    val index = if(event.which == BACKWARDS_KEY) currentSlideIndex - 1
                        else if(event.which == FORWARDS_KEY) currentSlideIndex + 1
                        else currentSlideIndex
    changeToSlide(index)
  }

  def safeSlideIndex(index:Int):Int = {
    if(index < 0) 0
    else if (index >= slides.length) slides.length -1
    else index
  }

  def changeToSlide(i:Int): JQuery = {

    currentSlideIndex = safeSlideIndex(i)
    val slideTags: TypedTag[Div] = slideHtml(currentSlideIndex)

    val body: JQuery = jQuery("body")
    body.html(slideTags.toString())

    val title: JQuery = jQuery("title")
    title.html(slides(currentSlideIndex).name)
  }

}