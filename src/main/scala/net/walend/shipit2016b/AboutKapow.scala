package net.walend.shipit2016b

import net.walend.present.SimpleSlide

import net.walend.present.Shortcuts._

/**
  *
  *
  * @author dwalend
  * @since 10/12/206
  */
object AboutKapow {

  val Cover = SimpleSlide ("Title",
    t("Kapow!"),
    t("How I Did It"),
    blank,
    st("David Walend"),
    st("HMS Catalyst Shipit, October, 2016"),
    st("Slides Online", "https://dwalend.github.io/Kapow/Kapow.html")   //todo how to get the javascript ap on line?
  )

  val ScalaJs = SimpleSlide("ScalaJs",
    t("Scala.JS","TODO link to sight"),
    l1("Write in Scala, compile to JavaScript (instead of JVM bytecodes)"),
    l1("Use the best language to reach the most viable platform"),
    l1("Potentially very valuable for SHRINE"),
    l2("Gives us the same logic in the front- and back-end code"),
    l2("Reuses our skillset"),
    l2("A better choice for low-frequency AWS Lambda serverless systems"),
    l1("TODO -- Li Hoyi", "TODO link to blog")
    //todo link to Li Hoyi's blog, to scala.js website
  )

  val LittleLanguage = SimpleSlide("LittleLanguage",
    t("A DSL for Presentations"),
    l1("Kapow supplies a little language to turn outlines into presentations"),
    c("""
        |   val Cover = SimpleSlide ("Title",
        |    t("Kapow!"),
        |    t("How I Did It"),
        |    blank,
        |    st("David Walend"),
        |    st("HMS Catalyst Shipit, October, 2016"),
        |    st("Slides Online", "https://dwalend.github.io/Kapow/Kapow.html")   //todo how to get the javascript ap on line?
        |  )
      """.stripMargin)
  )

  val SinglePageApp = SimpleSlide("SinglePageApp",
    t("A Presentation is a Single-Page Application"),
    l1("Kapow translates the outline into html tags via ScalaTags"), //todo link
    l1("jQuery picks up left- and right- arrow keys to navigage"), //todo link
    l1("Kapow uses ScalaTags to supply CSS") //todo link
  )


  val Abstract = SimpleSlide("Abstract",p(
    """Kapow! is a simple tool for turning outlines into slide presentations. Kapow! uses scalajs to drive jquery and a simple css and tag library via compiled Scala code. It includes a very simple little language for marking up an outline. jQuery code controls the presentation via left and right arrows. Specialized SVG graphics provide callouts for code, and a big finish for the presentation. """
  ))

  val Thanks = SimpleSlide("Thanks To",
    l2("Li Hoyi - Scala.JS, ScalaTags library, questions about abstracting text and tags"),
    l2("Greg - Pointer to Processing.js"),
    l2("Ben - span tag, yes use jQuery")
  )

  //todo shrine catalyst slide
  val ShipIt = SimpleSlide("HMS Catalyst Informatics ShipIt",p(
    """
      |SHRINE is a tool for discovering cohorts of similar patients for clinical trials and population studies by querying distributed EMR databases. Shrine started migrating (from Java) to Scala in 2008, making it one of the oldest open source Scala projects still running.
      |
      |Supported by the Harvard Catalyst Department for Clinical and Translational Sciences (Award 1 UL1 TR001102-01 from the National Center for Advancing TranslationalSciences [NCATS], a part of the National Institutes of Health)
    """.stripMargin
  ))

  val ScalaWhat = SimpleSlide("ScalaWhat",
    t("Scala"),
    l1("A Scalable Programming Language","http://www.scala-lang.org/what-is-scala.html"),
    l1("Scales in Scope"),
    l2("Scripting, Web pages, Desktop, Servers, Distributed - Both Homo- and Heterogeneous"),
    l1("Roots in Category Theory Make Code Expressive"),
    l1("Scales for Differences in Knowledge and Experience"),
    l2("Enables Different Coding Styles"),
    l2("Strong Support for Procedural, OO, and Functional Styles"),
    l1("Creators Intend it to Scale Through Time")
  )

  val CodeStyleFromLanguage = SimpleSlide("CodeStyleFromLanguage",
    t("Scala Enables Different Coding Styles"),
    l1("Strongly Typed"),
    l2("The Most Complete Type System in Common Use"),
    l1("Custom Control Flow"),
    l2("First-class Functions and Higher-Order Functions"),
    c("""  def configForTest[T](key:String,value:String)(block: => T):T = {
                |    val originalValue = System.getProperty(key)
                |    System.setProperty(key,value)
                |    try { block }
                |    finally { System.setProperty(key,originalValue) }
                |  }
                |...
                |  def testAutoApprove() {
                |    configForTest("shrine.steward.mode","AutoApprove"){
                |      ... //test code
                |    }
                |  }
                |
                |  """.stripMargin)
  )



  val slides = Seq(Cover,ScalaJs,LittleLanguage,SinglePageApp,Abstract,Thanks,ShipIt,ScalaWhat,CodeStyleFromLanguage)
}

//todo a slide about shrine and gage the room
