package tutorial.webapp


import net.walend.present.ToScalaTags
import net.walend.shipit2016b.AboutKapow
import utest._
import org.scalajs.jquery.jQuery

/**
  * @author dwalend
  * @since 10-20-2016
  */
object ToScalaTagsTest extends TestSuite {

  // Initialize App

  def tests = TestSuite {
    'ToScalaTags {

      val slides = AboutKapow.slides
      val markup = slides.map(ToScalaTags.toSlideHtml)

      markup.foreach(x => println(x))
    }

  }
}