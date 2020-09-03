package sections

import CVMeta
import pl.ukaszapps.itext.nodes.*
import sections.common.*
import java.io.File

data class Project(
    var code: String,
    var name: String,
    var from: String,
    var to: String? = null,
    var skillsUsed: List<String> = emptyList(),
    var company: String? = null
)

data class ProjectsData(var projects: List<Project> = emptyList())

data class UrlData(val title: String, val url: String)

data class ProjectDescription(val title: String? = null, val description: String, val urls: List<UrlData>? = null)

fun projectsSection(metadata: CVMeta): Node {
  val projectsBaseDir = File(metadata.publicDataBaseDir, "data/history")
  val projectsFile = File(projectsBaseDir, "projects.json")
  val projectsData = parse<ProjectsData>(projectsFile)
  val translations = metadata.translations
  return projectsData.render {


      fun Project.toNode(): Node {
          val projectDescriptionFile = File(projectsBaseDir, "$code/${metadata.lang}.json")
          val descriptionExist = projectDescriptionFile.exists()

          var description: ProjectDescription? = null
          if (descriptionExist) {
              description = parse<ProjectDescription>(projectDescriptionFile)
          }
          return Row(
                  weights = listOf(1.75f, 1f),
                  children = listOf(
                          Column(children = listOfNotNull(
                                  Text(description?.title ?: name, font = defaultBoldFont),
                                  description?.let { defaultText(it.description) }
                          ) +
                              (description?.urls?.map {
                                listOf(
                                Text(it.title+":", font = labelFont),
                                Text(it.url, font = captionFont)
                                )
                              }?.flatten()?.toList() ?: emptyList())
                          ),
                          Column(
                                  children = listOfNotNull(
                                          Align(
                                                  alignment = Alignment.RIGHT, child = defaultText("$from - ${to
                                                  ?: translations.getOrError("now")}")),
                                          this.company?.let {
                                              Align(
                                                      alignment = Alignment.RIGHT, child = defaultText(it))
                                          }
                                  )
                          )

                  )
          )
      }
      
      Column(
              children = listOf(listOf(
                      sectionText(translations.getOrError("workTimeline"))
              ),
                      it.projects.map(Project::toNode)
              ).flatten()
      )
  }
}