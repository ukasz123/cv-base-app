package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Chunk
import com.itextpdf.text.Element
import com.itextpdf.text.Font

class Text(private val text: String, private val font: Font = Font()) : Leaf {
  override fun render(): Element {
    return Chunk(text, font)
  }
}
