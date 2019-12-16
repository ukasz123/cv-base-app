import com.itextpdf.text.Document
import com.itextpdf.text.FontFactory
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import pl.ukaszapps.itext.nodes.Column
import sections.common.parseMap
import sections.contactSection
import sections.privateProjectsSection
import sections.projectsSection
import sections.skillsSection
import java.io.File
import java.io.FileOutputStream


data class CVMeta(val baseDir: File, val lang: String = "pl", val translations: Map<String, String> = emptyMap())

fun main(args: Array<String>) {
  val basePath = args[0]
  val author = if (args.size > 1) args[1] else null
  val baseDir = File(basePath)
  FontFactory.defaultEncoding = BaseFont.CP1250
  listOf("pl", "en").forEach { lang ->
    val meta = CVMeta(baseDir = baseDir, lang = lang, translations = parseMap(File(baseDir, "data/translations.$lang.json")))
    val document = Document(PageSize.A4, 50.0f, 50.0f, 50.0f, 50.0f)
    val writer = PdfWriter.getInstance(document, FileOutputStream("build/cv-$lang.pdf"))
    document.open()
    document.add(Column(
        children = listOf(
            contactSection(meta),
            skillsSection(meta),
            projectsSection(meta),
            privateProjectsSection(meta)
            )
    ).render())

    document.addTitle("CV (generated)")
    document.addCreationDate()
    document.addLanguage(lang)
    document.addCreator("CV Generator by Lukasz Huculak")
    author?.let { document.addAuthor(it) }
    document.close()
    writer.close()
  }
}


