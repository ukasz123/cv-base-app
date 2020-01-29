package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Chunk
import com.itextpdf.text.Element

private val emptyElement = Chunk()
object Empty: Leaf {
    override fun render(): Element = emptyElement
}