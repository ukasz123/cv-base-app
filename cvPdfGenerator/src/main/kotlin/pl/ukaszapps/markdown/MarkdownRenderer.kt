package pl.ukaszapps.markdown

import com.itextpdf.text.Element
import com.itextpdf.tool.xml.XMLWorkerHelper
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import pl.ukaszapps.itext.nodes.Column
import pl.ukaszapps.itext.nodes.Node

class MarkdownRenderer(private val markdown: String) : Node {
    override fun render(): Element {
        val flavour = CommonMarkFlavourDescriptor()
        // TODO: use better sanitization
        val sanitizedMarkdown = markdown.let {
            it.replace("<", "&lt;").replace(">", "&gt;")
        }
        val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(sanitizedMarkdown)
        val html = HtmlGenerator(sanitizedMarkdown, parsedTree, flavour).generateHtml()

        val elementList = XMLWorkerHelper.parseToElementList(html, "")

        val htmlCell = Column(elementList.map { ElementNode(it) })

        return htmlCell.render()
    }
}

private class ElementNode(private val element: Element) : Node {
    override fun render(): Element {
        return element
    }
}