package sections

import CVMeta
import pl.ukaszapps.itext.nodes.*
import sections.common.*
import sections.common.captionFont
import sections.common.getOrError
import sections.common.parse
import java.io.File

data class LanguagesData(val name: String, val details: String? = null)

data class KnownLanguages(val known: List<LanguagesData> = emptyList())

fun knownLanguagesSection(metadata: CVMeta): Node {
  val languagesDataFile = File(metadata.publicDataBaseDir, "data/foreign_languages/${metadata.lang}.json")
  val languagesData = parse<KnownLanguages>(languagesDataFile)
  return Column(
      children = listOf(
          listOf(sectionText(metadata.translations.getOrError("foreignLanguages"))),
          languagesData.known.mapIndexed { index, language ->
            Padding(
                padding = Dimensions(
                    top = if (index > 0) 16f else 0f
                ),
                child = Column(
                    children = listOf(
                        defaultText(language.name),
                        Text(language.details?:"", font = captionFont)
                    )
                )
            )
          }
      ).flatten()
  )
}