package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Element
import com.itextpdf.text.ListItem
import com.itextpdf.text.pdf.PdfPTable

abstract class TableContainer(children: List<Node>) : MultiChildContainer(children) {
  override fun render(): Element {
    return prepareTable().apply {
      widthPercentage = 100f
      children.forEach {
        addCell(defaultPdfPCell {
          addElement(it.render())
        })
      }
    }
  }

  abstract fun prepareTable(): PdfPTable
}

class Row(children: List<Node>, private val weights: List<Float>? = null) : TableContainer(children) {
  override fun prepareTable(): PdfPTable {
    return if (weights != null) {
      PdfPTable(weights.toFloatArray())
    } else {
      PdfPTable(children.size)
    }
  }
}

class Column(children: List<Node>) : TableContainer(children) {
  override fun prepareTable(): PdfPTable = PdfPTable(1)
}

enum class NodeListType {
  DEFAULT,
  NUMBERED,
  LETTERED
}

class NodeList(children: List<Node>, private val type: NodeListType = NodeListType.DEFAULT) : MultiChildContainer(children) {
  override fun render(): Element {
    return com.itextpdf.text.List(
        type != NodeListType.DEFAULT,
        type == NodeListType.LETTERED
    ).apply {
      children.forEach {
        add(ListItem().apply {
          add(it.render())
        })
      }
    }
  }

}