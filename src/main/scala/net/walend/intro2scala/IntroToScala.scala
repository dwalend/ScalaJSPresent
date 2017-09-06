package net.walend.intro2scala

import java.io.File

import net.walend.present.{MarkDown, Presentation, SimplePresentation, SimpleSlide}
import java.nio.file.{Files, Path, Paths}
import java.nio.charset.StandardCharsets

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object IntroToScala extends Presentation {

  val slides = Start.slides ++ ProsAndCons.slides ++ Slick.slides ++ Spray.slides ++ WrapUp.slides
    /*
  def main (args: Array[String]) {
    val presentation = SimplePresentation(slides)

    val siteOut = new File("deck")
    siteOut.mkdir()

    val markdown: Seq[String] = MarkDown.render(presentation)
    val paths = presentation.slides.map(x => Paths.get(s"${siteOut.toString}/${x.name}.md"))
    paths.zip(markdown).foreach(x => writeTextToFile(x._2,x._1))
  }

  def writeTextToFile(text:String,path:Path):Unit = {
    Files.write(path, text.getBytes(StandardCharsets.UTF_8))
  }
  */
}
