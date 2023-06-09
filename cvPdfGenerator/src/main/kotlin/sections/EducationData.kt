package sections

import CVMeta
import pl.ukaszapps.itext.nodes.*
import sections.common.*
import sections.common.defaultBoldFont
import sections.common.getOrError
import sections.common.parse
import java.io.File

data class Education(val title: String, val period: String)

data class EducationData(val college_studies: List<Education>)

fun educationSection(meta: CVMeta): Node {
    val educationDataFile = File(meta.publicDataBaseDir, "data/education/${meta.lang}.json")
    val educationData = parse<EducationData>(educationDataFile)
    val translations = meta.translations
    return educationData.render {
        Column(
                children = listOf(
                        sectionText(translations.getOrError("education")),
                        Column(
                                children = it.college_studies.mapIndexed { index, e ->
                                    Row(
                                            children = listOf(
                                                    Align(child = Text("${index + 1}.\t${e.title}", font = defaultBoldFont), alignment = Alignment.LEFT),
                                                    Align(
                                                            alignment = Alignment.RIGHT,
                                                            child = defaultText(e.period)
                                                    )
                                            )
                                    )
                                }
                        )
                )
        )
    }
}