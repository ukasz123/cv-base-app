package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Element
import com.itextpdf.text.pdf.PdfPCell

internal inline fun defaultPdfPCell(body: PdfPCell.() -> Unit): PdfPCell =
  PdfPCell().apply {
    border = 0
    verticalAlignment = Element.ALIGN_TOP
    horizontalAlignment = Element.ALIGN_LEFT
  }.apply(body)
