package net.walend.present

import net.walend.present.Style._
import org.scalajs.dom.html.{Anchor, Div, Element, Span}

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

/**
  * Render a slide stack in MarkDown
  *
  * @author david
  * @since 10/12/16
  */
object ToScalaTags {

  def toPresentationHtml(presentation:Presentation) = {

    val slideText = presentation.slides.map(toSlideHtml)
  }

  def fileName(slide:Slide):String = {
    s"${slide.name}.html"
  }

  def toSlideHtml(slide:Slide): TypedTag[Div] = {
    div(slide.items.map(toTag))
  }

  def toTag(item:Item): TypedTag[Element] = {
    item match {
      case blankLine: Line if blankLine == BlankLine => toTag(blankLine)
      case codeBlock:CodeBlock => toTag(codeBlock)
      case fragLine:FragmentLine => toTag(fragLine)
      case table:Table => toTableMarkup(table)
    }
  }

  def toTag(blankLine: Line) = br()

  def toTag(codeBlock: CodeBlock): TypedTag[Element] = {
    code(codeBlock.code)
  }

  def toTag(line: FragmentLine):TypedTag[Element] = {
    val parts = line.fragments.map(toFrag)

    lineTag(line.style)(parts)
  }

  def toFrag(fragment:Fragment):Frag = {
    fragment match {
      case text:TextFragment => toFrag(text)
      case link:LinkFragment => toFrag(link)
      case emptyFragment:Fragment if emptyFragment == EmptyFragment => br()
    }
  }

  def toFrag(linkFragment: LinkFragment): TypedTag[Anchor] = {
    a(href:=linkFragment.urlText)(toFrag(linkFragment.frag))
  }

  def toFrag(textFragment: TextFragment):Frag = {
    val typedTag: Option[TypedTag[Element]] = fragmentTag.get(textFragment.style)

    val noneResult:Frag = StringFrag(textFragment.text)
    typedTag.fold(noneResult)(tag => tag(textFragment.text))
  }

  def codeBlock(contents:Seq[_root_.scalatags.JsDom.Modifier]):TypedTag[Element] =
    pre(script(
      """
        | $(document).ready(function() {
        |  $('code').each(function(i, block) {
        |    hljs.highlightBlock(block);
        |  });
        |});
      """.stripMargin),
      code(`class`:="scala")(contents)) //todo do better than just shoving in some javascript

  val lineTag: Map[Style, (Seq[_root_.scalatags.JsDom.Modifier]) => TypedTag[Element]] = Map(
    Title -> h1(style:="text-align:center").apply,
    SubTitle -> h2(style:="text-align:center").apply,
    HeadLine -> h2.apply,
    SupportLine -> h3.apply,
    TertiaryLine -> h4.apply,
    ScalaCode -> codeBlock,    //needs the pre tag, too
    Quote -> blockquote.apply,
    Plain -> p.apply
  )

  val fragmentTag: Map[Style, TypedTag[Element]] = Map(
    ScalaCode -> code(`class`:="scala") //todo use codeBlock
  )

  def toTableMarkup(tab:Table):TypedTag[Element] = {
    table(th(tab.headRow.map(toTag)),tab.contents.map(row => tr(row.map(cell => td(toTag(cell))))))
  }
}
