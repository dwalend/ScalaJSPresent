package net.walend.shipit2016b

import net.walend.present.SimpleSlide

import net.walend.present.Shortcuts._

/**
  *
  *
  * @author dwalend
  * @since 10/20/206
  */
object AboutKapow {

  val Cover = SimpleSlide ("Kapow!",
    t("Kapow!"),
    st("Outlines to Presentations in Scala.js"),
    blank,
    st("David Walend, Ben Carmen, ..."),
    st("HMS Catalyst Shipit, October, 2016")
//    st("Slides Online", "https://dwalend.github.io/Kapow/Kapow.html")   //todo how to get the javascript ap on line?
  )

  val ScalaJs = SimpleSlide("Scala.js",
    t("Scala.JS","https://www.scala-js.org/"),
    l1("Write in Scala, compile to JavaScript (instead of JVM bytecodes)"),
    l1("Use the best language to reach the most viable platform"),
    l1("Potentially very valuable for SHRINE"),
    l2("Gives us the same logic in the front- and back-end code"),
    l2("Reuses our skillset"),
    l2("A better choice for low-frequency AWS Lambda serverless systems"),
    l1(f("Li Hoyi's blog on "),f("why Scala.js","http://www.lihaoyi.com/"))  //todo fix link
  )

  val LittleLanguage = SimpleSlide("Little Language",
    t("A DSL for Presentations"),
    l1("Kapow! supplies a little language to turn outlines into presentations"),
    c("""
        |  val ScalaJs = SimpleSlide("Scala.Js",
        |    t("Scala.JS","https://www.scala-js.org/"),
        |    l1("Write in Scala, compile to JavaScript (instead of JVM bytecodes)"),
        |    ...
        |    l2("A better choice for low-frequency AWS Lambda serverless systems"),
        |    l1(f("Li Hoyi's blog on "),f("why Scala.js","http://www.lihaoyi.com/"))
        |  )
      """.stripMargin)
  )

  val SinglePageApp = SimpleSlide("SinglePageApp",
    t("A Presentation is a Single-Page Application"),
    l1(f("Kapow! translates the outline into html tags via "),f("ScalaTags","http://www.lihaoyi.com/scalatags/#ScalaTags")),
    l1("jQuery picks up left- and right- arrow keys to navigage"),
    l1("Simple CSS to set the look"),
    l1(f("highlights.js","https://highlightjs.org/usage/"),f(" to color the code"))
  )


  val Thanks = SimpleSlide("Thanks To",
    l2("Li Hoyi - Scala.JS, ScalaTags library, questions about abstracting text and tags"),
    l2("isagalaev - highlights.js"),
    l2("Greg - Pointer to Processing.js"),
    l2("""Ankit - "Just put the link tag there and it'll pull in the CSS""""),
    l2("""Sherry - "That part is working fine... Read the docs""""),
    l2("Ben - div tag, yes use jQuery, peer programming CSS")
  )

  val slides = Seq(Cover,ScalaJs,LittleLanguage,SinglePageApp,Thanks)
}

//todo a slide about shrine and gage the room
