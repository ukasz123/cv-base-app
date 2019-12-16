package sections

import CVMeta
import pl.ukaszapps.itext.nodes.Column
import pl.ukaszapps.itext.nodes.Node
import pl.ukaszapps.itext.nodes.Text
import sections.common.*
import sections.common.defaultBoldFont
import sections.common.defaultFont
import sections.common.getOrError
import sections.common.parse
import java.io.File

data class PrivateProject(
    val title: String,
    val description: String,
    val repositoryUrl: String,
    val skills: List<String> = emptyList()
)

data class PrivateProjects(val projects: List<PrivateProject> = emptyList())

fun privateProjectsSection(metadata: CVMeta): Node {
  val translations = metadata.translations
  val privateProjectsFile = File(metadata.baseDir, "data/other_projects/${metadata.lang}.json")
  val privateProjectsData = parse<PrivateProjects>(privateProjectsFile)
  return Column(
      children = listOf(
          listOf(sectionText(translations.getOrError("otherProjects"))),
          privateProjectsData.projects.map {
            Column(children = listOf(
                Text(it.title, font = defaultBoldFont),
                Text(it.description, font = defaultFont),
                Text(it.repositoryUrl, font = captionFont)
            ))
          }
      ).flatten()
  )

}