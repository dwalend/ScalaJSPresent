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
    t("With Examples Using Slick and Akka-http"),
    blank,
    st("David Walend"),
    st("Boston Scala Meetup, September 6th, 2017"),
    st("Slides Online (MarkDown)", "https://dwalend.github.io/IntroScalaTalk/Cover.md") //todo new version. Also, can you hang .js files?
  )

  val Abstract = SimpleSlide("Abstract",
    p("""Scala – a scalable programming language – has roots in academia and is swiftly being adopted by industry. Scala scales down to scripts and single-page web-apps, up to distributed stream-processing systems, can be used by novices and masters, and promises to scale through time to support long-lived projects. I will use examples from SHRINE - a tool for querying distributed demographic, diagnostic, and treatment data - to show Scala's strengths and weaknesses, and share my own experience with Scala."""),
    p(
      """
        |David Walend is the tech lead and back-end developer for HMS SHRINE, a system for providing access to nationally distributed patient medical records while protecting patient privacy. SHRINE is one of "the oldest Scala open source projects that actually does something." David began using Scala in hobby code in 2011, and at work in 2013. He has 25 years of experience as a software developer, primarily focusing on geographically distributed resilient back-end systems, providing him a deep well of anecdotes.
      """.stripMargin
    )

  )

  //todo shrine catalyst slide

  val Outline = SimpleSlide("Outline",
    l1("Scala"),
    l2("A Scalable Language"),
    l1("Examples use:"),
    l2("Slick: A Functional Database Library"),
    l2(TextFragment("Spray.io",Style.StrikeThrough),f(" Akka-Http: A Micro Web Service Framework")),
    blank,
    l2("Examples are from HMS Catalyst SHRINE")
  )

  val ScalaWhat = SimpleSlide("ScalaWhat",
    TextLine("Scala",Style.Title),
    LinkTextLine("A Scalable Programming Language","http://www.scala-lang.org/what-is-scala.html",Style.HeadLine),
    TextLine("Scales in Scope",Style.HeadLine),
    TextLine("Scripting, Web pages, Desktop, Servers",Style.SupportLine),
    TextLine("Distributed - Homogeneous and Heterogeneous",Style.SupportLine),
    TextLine("Java VM, Javascript in browsers, Native",Style.SupportLine),
    TextLine("Roots in Category Theory Help Code Be Expressive"),
    TextLine("Enables Different Coding Styles",Style.SupportLine),
    TextLine("Strong Support for Procedural, OO, and Functional Styles",Style.SupportLine),
    TextLine("Scales for Differences in Knowledge and Experience",Style.HeadLine),
    TextLine("Creators Intend Scala to Scale Through Time",Style.HeadLine)
  )

  val CodeStyle = SimpleSlide("CodStyle",
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Extremely Clear Code",Style.HeadLine),
    TextLine("Choose the Style That Best Matches the Task at Hand",Style.SupportLine),
    TextLine("Different Styles Can Be Made From Language Primitives",Style.HeadLine),
    TextLine("Not Language Changes",Style.SupportLine),
    TextLine("Solid Platform for Domain-Specific Languages",Style.SupportLine)
  )

  val CodeStyleFromLanguage = SimpleSlide("CodeStyleFromLanguage",
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Strongly Typed - the Most Complete Type System in Use",Style.HeadLine),
    TextLine("Custom Control Flow",Style.HeadLine),
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
    TextLine("Functional Programming with Option[T]",Style.Title),
    TextLine("Handle Empty Fields With Container",Style.HeadLine),
    FragmentLine(Seq(
      TextFragment("Replace Nulls And Checks, Fixes Sir Tony Hoare's "),
      LinkFragment(""""billion-dollar mistake ... the invention of the null reference in 1965"""","http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions",Style.SupportLine
      ))),
    LinkTextLine(""""billion-dollar mistake ... the invention of the null reference in 1965"""","http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions",Style.SupportLine),
    TextLine("Option[T] is an abstract class with two subclasses",Style.HeadLine),
    TextLine("Some - When a Value is Present",Style.SupportLine),
    TextLine("None - When No Value is Present",Style.SupportLine),
    TextLine("Takes a type parameter T",Style.SupportLine),
    CodeBlock(
      """val someString:Option[String] = Option("a string") // == Some("a string")
        |val noString:Option[String] = None
        |val notSure:Option[String] = Option(null) // == None""".stripMargin)
  )

  //todo add practical example from yesterday - fixing a BlockingQueue's poll method

  val FuncFold = SimpleSlide("FuncFold",
    TextLine("Functional Programming with Option's fold()()",Style.Title),
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

  val slides = Seq(Cover,Abstract,Outline,ScalaWhat,CodeStyle,CodeStyleFromLanguage,FuncOption,FuncFold)
}

//todo a slide about shrine and gage the room
