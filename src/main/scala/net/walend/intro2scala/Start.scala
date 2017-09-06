package net.walend.intro2scala

import net.walend.present.{BlankLine, CodeBlock, FragmentLine, LinkFragment, LinkTextLine, SimpleSlide, Style, TextFragment, TextLine}
import net.walend.present.Shortcuts._

/**
 * @author david 
 * @since 4/29/15
 */

object Start {

  val Cover = SimpleSlide ("Cover",
    t("An Introduction To Scala"),
    t("With Examples Using Slick and Akka-Http"),
    blank,
    st("David Walend"),
    st("Boston Scala Meetup, September 6th, 2017"),
    st("Slides Online", "https://dwalend.github.io/ScalaJSPresent/scalajs-tutorial-fastopt.html") 
  )

  val Abstract = SimpleSlide("Abstract",
    p("""Scala – a scalable programming language – has roots in academia and is swiftly being adopted by industry. Scala scales down to scripts and single-page web-apps, up to distributed stream-processing systems, can be used by novices and masters, and promises to scale through time to support long-lived projects. I will use examples from SHRINE - a tool for querying distributed demographic, diagnostic, and treatment data - to show Scala's strengths and weaknesses, and share my own experience with Scala."""),
    p(
      """
        |David Walend is the tech lead and back-end developer for HMS SHRINE, a system for providing access to nationally distributed patient medical records while protecting patient privacy. SHRINE is one of "the oldest Scala open source projects that actually does something." David began using Scala in hobby code in 2011, and at work in 2013. He has 25 years of experience as a software developer, primarily focusing on geographically distributed resilient back-end systems, providing him a deep well of anecdotes.
      """.stripMargin
    )

  )

  val Outline = SimpleSlide("Outline",
    t("Scala"),
    l1("A Scalable Language"),
    l1("Examples use:"),
    l2("Slick: A Functional Database Library"),
    l2(TextFragment("Spray.io",Style.StrikeThrough),f(" Akka-Http: A Web Micro Service Framework")),
    l2("Examples are from HMS Catalyst SHRINE (NIH Funded)")
  )

  val ScalaWhat = SimpleSlide("ScalaWhat",
    t(f("Scala: "),f("A Scalable Programming Language","http://web.archive.org/web/20170702203348/http://www.scala-lang.org/what-is-scala.html")),
    TextLine("Scales in Scope",Style.HeadLine),
    TextLine("Scripting, Web pages, Desktop, Servers",Style.SupportLine),
    TextLine("Distributed - Homogeneous and Heterogeneous",Style.SupportLine),
    TextLine("Java VM, Javascript in browsers, Android, Native",Style.SupportLine),
    l1("Enables Different Coding Styles"),
    TextLine("Strong Support for Procedural, OO, and Functional Styles",Style.SupportLine),
    l2("Roots in Category Theory Help Code Be Expressive"),
    TextLine("Scales for Differences in Knowledge and Experience",Style.HeadLine),
    TextLine("Creators Intend Scala to Scale Through Time",Style.HeadLine)
  )

  val CodeStyle = SimpleSlide("CodStyle",
    t("Scala Enables Different Coding Styles"),
    TextLine("Extremely Clear Code",Style.HeadLine),
    TextLine("Choose the Style That Best Matches the Task at Hand",Style.SupportLine),
    TextLine("Different Styles Can Be Made From Language Primitives",Style.HeadLine),
    TextLine("Libraries, Not Language Changes",Style.SupportLine),
    TextLine("Solid Platform for Domain-Specific Languages",Style.SupportLine)
  )

  val CodeStyleFromLanguage = SimpleSlide("CodeStyleFromLanguage",
    t("Scala Enables Different Coding Styles"),
    TextLine("Strongly Typed - With a Turing Complete Type System",Style.HeadLine),
    TextLine("Easy Custom Control Flow",Style.HeadLine),
    TextLine("Via First-Class Functions and Higher-Order Functions",Style.HeadLine),
    CodeBlock("""  def configForTest[T](key:String,value:String)(block: => T):T = {
                |    val originalValue = System.getProperty(key)
                |    System.setProperty(key,value)
                |    try { block }
                |    finally { System.setProperty(key,originalValue) }
                |  }
                |
                |  def testAutoApprove() {
                |    configForTest("shrine.steward.mode","AutoApprove"){
                |      ... //test code block
                |    }
                |  }""".stripMargin)
  )

  val FuncOption = SimpleSlide("FuncOption",
    t("Functional Programming with Option[T]"),
    TextLine("Handle Empty Fields With a Collection of Zero or One Thing",Style.HeadLine),
    FragmentLine(Seq(
      TextFragment("Replace Nulls And Checks to fix Sir Tony Hoare's "),
      LinkFragment(""""billion-dollar mistake ... the invention of the null reference in 1965"""","http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions",Style.SupportLine
      ))),
    TextLine("Option[T] is a sealed abstract class with two subclasses",Style.HeadLine),
    TextLine("Some - When a Value is Present",Style.SupportLine),
    TextLine("None - When No Value is Present",Style.SupportLine),
    TextLine("Takes a type parameter T",Style.SupportLine),
    CodeBlock(
      """val someString:Option[String] = Option("a string") // == Some("a string")
        |val noString:Option[String] = None
        |val notSure:Option[String] = Option(null) // == None""".stripMargin)
  )

  val OptionBlockingQueue = SimpleSlide("OptionBlockingQueue",
    t("Get Rid of nulls With Option"),
    TextLine("BlockingQueue.poll() returns either a value or null",Style.HeadLine),
    CodeBlock(
      s"""val blockingQueue:BlockingQueue[String] = ...
         |
         |Option(blockingQueue.poll()).foreach{ string:String => println(string)}""".stripMargin),
    TextLine("foreach() takes some f(s:String) as a parameter",Style.SupportLine),
    TextLine("For the one s in Some(s) foreach() Evaluates f(s)",Style.SupportLine),
    TextLine("For None foreach() does nothing",Style.SupportLine)
  )

  val FuncFold = SimpleSlide("FuncFold",
    t("Functional Programming with Option's fold()()"),
    TextLine("Option[T]'s fold()() method takes two functions"),
    TextLine("def fold[Out](isNone: => Out)(isSome: (T) => Out): Out",Style.ScalaCode),
    TextLine("None[T].fold()() evaluates the left (no-argument) function",Style.SupportLine),
    TextLine("Some[T].fold()() evaluates the right function with the value as the parameter",Style.SupportLine),
    CodeBlock(
      s"""val yourOption:Option[String] = ...
         |val stringToPrint = yourOption.fold("*")(string => s"!$$string!")
         |//None returns "${None.fold("*")(string => s"!$string!")}"
         |//Some("a string") returns "${Some("a string").fold("*")(string => s"!$string!")}"""".stripMargin),
    LinkTextLine("Read more about fold()()","https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright",Style.SupportLine)
  )

  val slides = Seq(Cover,Abstract,Outline,ScalaWhat,CodeStyle,CodeStyleFromLanguage,FuncOption,OptionBlockingQueue,FuncFold)
}