package net.walend.intro2scala

import net.walend.present.{BlankLine, LinkTextLine, Style, TextLine, SimpleSlide}
import net.walend.present.Shortcuts._

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object WrapUp {

  val WrapUp = SimpleSlide("WrapUp",
    t("Scala Leads Where We Are Going"),
    TextLine("Scala Can Adopt New Code Styles",Style.HeadLine),
    TextLine("Java Follows Scala: JDK8 Streams, Lambdas, Optional",Style.SupportLine),
    TextLine("Even More Awkward For Other Language Ecosystems",Style.SupportLine),
    TextLine("But Project Dotty Reworks and Simplifies Scala's Type System",Style.SupportLine),
    TextLine("Modern Hardware Provides More Cores, Spread Out, Different",Style.HeadLine),
    TextLine("No Longer a Faster Solver For the Halting Problem",Style.SupportLine),
    TextLine("Functional Programming Can Get Back Your 'Free Lunch'",Style.SupportLine),
    TextLine("Big Data Tools Are Written in Scala - Spark, Storm, Neo4J, Many Others",Style.SupportLine),
    TextLine("Scala Native will provide access to bare metal hardware",Style.SupportLine),
    TextLine("Scala.js Compiles Scala to JavaScript",Style.HeadLine),
    TextLine("Even Browser Code Could Be Fun",Style.SupportLine)
  )

  val PartingQuote = SimpleSlide("PartingQuote",
    TextLine("Always favor readability ...",Style.Quote),
    TextLine("... take advantage of simple language features that afford great power but avoid the esoteric ones ...",Style.Quote),
    TextLine("... complexity is the tax of sophistication - you must always ensure that its utility exceeds its cost.",Style.Quote),
    BlankLine,
    LinkTextLine("Twitter's Effective Scala","http://twitter.github.io/effectivescala/",Style.Plain)

  )

  val slides = Seq(WrapUp,PartingQuote)

}
