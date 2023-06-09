import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.ColumnText
import com.itextpdf.text.pdf.PdfPageEventHelper
import com.itextpdf.text.pdf.PdfWriter
import pl.ukaszapps.itext.nodes.Column
import pl.ukaszapps.itext.nodes.Row
import sections.*
import sections.common.getOrError
import sections.common.parseMap
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.util.*


data class CVMeta(val publicDataBaseDir: File, val lang: String = "pl", val translations: Map<String, String> = emptyMap(), val privateDataBaseDir: File)

fun main(args: Array<String>) {
  val basePath = args[0]
  val author = if (args.size > 1) args[1] else null
  val baseDir = File(basePath)
  val publicDataBaseDir = File(baseDir, "public")
  val privateDataBaseDir = File(baseDir, "private")
  FontFactory.defaultEncoding = BaseFont.CP1250
  listOf("pl", "en").forEach { lang ->
    val footerTranslation = parseMap(File("footer_$lang.json"))
    val meta = CVMeta(publicDataBaseDir = publicDataBaseDir, privateDataBaseDir = privateDataBaseDir, lang = lang, translations = parseMap(File(publicDataBaseDir, "data/translations.$lang.json")))
    val document = Document(PageSize.A4, 50.0f, 50.0f, 50.0f, 50.0f)
    val outputFile = File(publicDataBaseDir,"generated/cv-$lang.pdf")
    if (!outputFile.parentFile.exists()){
      outputFile.parentFile.mkdirs()
    }
    val writer = PdfWriter.getInstance(document, FileOutputStream(outputFile))

    val dateString = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.forLanguageTag(lang)).format(Date())
    writer.pageEvent = CVFooter("${footerTranslation.getOrError("footer_prefix")}$dateString")

    document.open()
    document.add(Column(
        children = listOf(
            contactSection(meta),
            skillsSection(meta),
            projectsSection(meta),
            privateProjectsSection(meta),
            Row(children = listOf(educationSection(meta), knownLanguagesSection(meta))),
            hobbiesSection(meta)
        )
    ).render())
    document.addTitle("CV ${author ?: ""} (generated)")
    document.addCreationDate()
    document.addLanguage(lang)
    document.addCreator("CV Generator by Łukasz Huculak")
    author?.let { document.addAuthor(it) }
    document.close()
    writer.close()
  }
}

private class CVFooter(private val footerText: String) : PdfPageEventHelper() {
  override fun onEndPage(writer: PdfWriter, document: Document) {
    super.onEndPage(writer, document)
    val cb = writer.directContent
    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
        Phrase(footerText, FontFactory.getFont(FontFactory.TIMES, 10f, Font.NORMAL, BaseColor.LIGHT_GRAY)),
        (document.right() - document.left()) / 2 + document.leftMargin(),
        document.bottom() - 10, 0f)
  }
}

