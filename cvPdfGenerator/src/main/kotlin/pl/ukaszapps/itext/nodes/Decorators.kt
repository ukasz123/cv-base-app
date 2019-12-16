package pl.ukaszapps.itext.nodes

import com.itextpdf.text.BaseColor
import com.itextpdf.text.Element
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPTable

data class Dimensions(val left: Float = 0f, val top: Float = 0f, val right: Float = 0f, val bottom: Float = 0f) {
  constructor(horizontal: Float = 0f, vertical: Float = 0f) : this(left = horizontal, right = horizontal, top = vertical, bottom = vertical)
  constructor(all: Float = 0f) : this(horizontal = all, vertical = all)
}

class Padding(private val padding: Dimensions, child: Node) : SingleChildContainer(child) {
  override fun render(): Element {
    return PdfPTable(1).apply {

      widthPercentage = 100f
      addCell(defaultPdfPCell {
        paddingLeft = padding.left
        paddingRight = padding.right
        paddingTop = padding.top
        paddingBottom = padding.bottom
        addElement(child.render())
      })
    }
  }

}

data class SideBorder(
    val width: Float = 0f,
    val color: BaseColor = BaseColor.BLACK
)

private val ZERO_SIDE_BORDER = SideBorder()

data class Border(
    val left: SideBorder = ZERO_SIDE_BORDER,
    val top: SideBorder = ZERO_SIDE_BORDER,
    val right: SideBorder = ZERO_SIDE_BORDER,
    val bottom: SideBorder = ZERO_SIDE_BORDER
) {
  internal val boxValue: Int =
      left.width.normalize() * Rectangle.LEFT + right.width.normalize() * Rectangle.RIGHT + top.width.normalize() * Rectangle.TOP + bottom.width.normalize() * Rectangle.BOTTOM

  constructor(horizontal: SideBorder, vertical: SideBorder) : this(left = horizontal, right = horizontal, top = vertical, bottom = vertical)
  constructor(all: SideBorder) : this(horizontal = all, vertical = all)
}


private fun Float.normalize(): Int = when {
  this > 0 -> 1
  this < 0 -> -1
  else -> 0
}

class BorderedBox(child: Node, private val border: Border) : SingleChildContainer(child) {
  override fun render(): Element {

    val b = border
    return PdfPTable(1).apply {
      widthPercentage = 100f
      addCell(defaultPdfPCell {
        border = b.boxValue
        borderColorBottom = b.bottom.color
        borderColorTop = b.top.color
        borderColorLeft = b.left.color
        borderColorRight = b.right.color

        borderWidthBottom = b.bottom.width
        borderWidthLeft = b.left.width
        borderWidthRight = b.right.width
        borderWidthTop = b.top.width
        addElement(child.render())
      })
    }
  }
}
enum class Alignment {
  LEFT, RIGHT, CENTER
}
class Align(child: Node, private val alignment:Alignment) : SingleChildContainer(child) {
  override fun render(): Element {
    val a = alignment
    return Paragraph().apply {
      alignment = when (a){
        Alignment.LEFT -> Element.ALIGN_LEFT
        Alignment.RIGHT -> Element.ALIGN_RIGHT
        Alignment.CENTER -> Element.ALIGN_CENTER
      }
      add(child.render())
    }
  }

}
