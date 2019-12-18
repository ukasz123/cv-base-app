package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Element
import com.itextpdf.text.Image

class Image(private val path: String) : Node {
  override fun render(): Element {
    return Image.getInstance(path)
  }

}