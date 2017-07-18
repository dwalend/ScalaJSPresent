package net.walend.shipit2017a

import net.walend.present.{IFrame, SimpleSlide, Table}
import net.walend.present.Shortcuts._

/**
  *
  *
  * @author dwalend
  * @since 10/20/206
  */
object MoreKapow {

  val Cover = SimpleSlide ("Return of the Tech Talks",
    t("Return of the Tech Talks"),
    st("Tech Talks Once Per Month","https://open.med.harvard.edu/wiki/display/CATSAND/Tech+Talks"),
    blank,
    st("Jillian Dudek, David Walend"),
    st("HMS Catalyst Shipit, June, 2017")
  )

  val TechTalks = SimpleSlide("Tech Talks",
    t("Tech Talks"),
    l1("Revival of Dev Club"),
    l1("Once a month - planned through November"),
    l1("All of Catalyst Informatics is Welcome"),
    l1("Talks will drill deeply into technical details"),
    l1("Come geek out with us")
  )

  val TechTalksAudience = SimpleSlide("Tech Talk Topics",
    t("Tech Talk Topics"),
    l1("Technology and Working with Technologists"),
    l1("Technical Tools"),
    l1("Coding Practices"),
    l1("How We Work Together"),
    l1("Expect to see code, stats, diagrams")
  )

  val WikiPage = SimpleSlide("Tech Talk Wiki Page",
    t("Tech Talk Wiki Pages"),
    l1("Upcoming and Past Talks","https://open.med.harvard.edu/wiki/display/CATSAND/Tech+Talks"),
    l1("Ideas","https://open.med.harvard.edu/wiki/display/CATSAND/Tech+Talk+Topic+Ideas")
  )

  val MoreKapowCover = SimpleSlide ("More Kapow!",
    t("More Kapow!"),
    st("Outlines to Presentations in Scala.js"),
    st("Now With More Features"),
    blank,
    st("David Walend, Ben Carmen, ..."),
    st("HMS Catalyst Shipit, June, 2017")
//    st("Slides Online", "https://dwalend.github.io/Kapow/Kapow.html")   //todo how to get the javascript ap on line?
  )

  val TableSlide = SimpleSlide("Tables",
    t("Tables"),
    Table(Array(l2("Table header")),Array(Array(l2("Cell 1"),l2("Cell 2"))))
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
        |  val SinglePageApp = SimpleSlide("SinglePageApp",
        |    t("A Presentation is a Single-Page Application"),
        |    l1(f("Kapow! translates the outline into html tags via "),f("ScalaTags","http://www.lihaoyi.com/scalatags/#ScalaTags")),
        |    l1("jQuery picks up left- and right- arrow keys to navigage"),
        |    l1("Simple CSS to set the look"),
        |    l1(f("highlights.js","https://highlightjs.org/usage/"),f(" to color the code"))
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

  val JqueryInScala = SimpleSlide("JQuery From Scala",
    t(""""Javascript is like assembly code. It needs a compiler." - Mark Waks"""),
    c("""
        |  def keyPressed(event:JQueryEventObject) = {
        |    val BACKWARDS_KEY = 37
        |    val FORWARDS_KEY = 39
        |
        |    val index = if(event.which == BACKWARDS_KEY) currentSlideIndex - 1
        |                        else if(event.which == FORWARDS_KEY) currentSlideIndex + 1
        |                        else currentSlideIndex
        |    changeToSlide(index)
        |  }
      """.stripMargin)
  )

  val Thanks = SimpleSlide("Thanks To",
    l2("Li Hoyi - Scala.JS, ScalaTags library, questions about abstracting text and tags"),
    l2("Mark Waks - Local Scala.JS evangelist"),
    l2("isagalaev - highlights.js"),
    l2("Greg - Pointer to Processing.js"),
    l2("""Ankit - "Just put the link tag there and it'll pull in the CSS""""),
    l2("""Sherry - "That part is working fine... Read the docs""""),
    l2("Ben - div tag, yes use jQuery, peer programming CSS")
  )

  val slides = Seq(Cover,TechTalks,TechTalksAudience,WikiPage)//MoreKapowCover,TableSlide,ScalaJs,JqueryInScala,SinglePageApp,LittleLanguage,Thanks)
}

//todo a slide about shrine and gage the room
