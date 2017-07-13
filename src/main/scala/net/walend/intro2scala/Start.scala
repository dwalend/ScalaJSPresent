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
    st("Boston Scala Meetup, May 20th, 2015"), //todo new date
    st("Slides Online (MarkDown)", "https://dwalend.github.io/IntroScalaTalk/Cover.md") //todo new version. Also, can you hang .js files?
  )

  val Abstract = SimpleSlide("Abstract",p(
    """The Boston Area Scala Enthusiasts MeetUp has had several requests for an introductory level talk on Scala. I recently put such a talk together for my coworkers at HMS that I'd like to share. I'll describe a bit about how Scala's founders intended it to be a scalable programming language, and cite examples that show some success. I will also share some anecdotes from my own career regarding pros and cons of Scala at work and in hobby code. Along the way I'll introduce case classes, Options, a little functional programming, Slick -- a database library, and Spray -- a web service library, all through example code pulled out of source control at my day job."""
  ))

  val Outline = SimpleSlide("Outline",
    l1("Scala"),
    l2("A Scalable Language"),
    l1("Slick"),
    l2("A Functional Database Library"),
    l1("Akka-http"),
    l2("A Micro Web Service Framework"),
    blank,
    l2("Examples from HMS Catalyst SHRINE Data Steward App")
  )

  //todo shrine catalyst slide

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
    TextLine("- Choose the Style That Best Matches the Task at Hand",Style.HeadLine),
    TextLine("Different Styles Can Be Made From Language Primitives",Style.HeadLine),
    TextLine("- Not Language Changes",Style.HeadLine),
    TextLine("Solid Platform for Domain-Specific Languages",Style.HeadLine)
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

  val FuncFold = SimpleSlide("FuncFold",
    TextLine("Functional Programming with Option's fold()()",Style.HeadLine),
    BlankLine,
    TextLine("Option[T]'s fold()() method takes two functions"),
    TextLine("def fold[Out](ifNone: => Out)(ifSome: (T) => Out): Out",Style.ScalaCode),
    TextLine("None[T].fold()() evaluates the left (no-argument) function",Style.SupportLine),
    TextLine("Some[T].fold()() evaluates the right function with the value as the parameter",Style.SupportLine),
    CodeBlock(
      s"""val yourOption:Option[String] = ...
         |val stringToPrint = yourOption.fold(".")(string => s"!$$string!")
         |//None returns "${None.fold(".")(string => s"!$string!")}"
         |//Some("a string") returns "${Some("a string").fold(".")(string => s"!$string!")}"""".stripMargin),
    LinkTextLine("Read more about fold()()","https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright",Style.SupportLine)
  )

  val slides = Seq(Cover,Abstract,Outline,ScalaWhat,CodeStyle,CodeStyleFromLanguage,FuncOption,FuncFold)
}

//todo a slide about shrine and gage the room
