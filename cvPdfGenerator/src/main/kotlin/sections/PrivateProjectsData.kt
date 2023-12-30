package sections

import CVMeta
import pl.ukaszapps.itext.nodes.*
import pl.ukaszapps.markdown.MarkdownRenderer
import sections.common.*
import sections.common.defaultBoldFont
import sections.common.getOrError
import sections.common.parse
import sections.icons.Skills
import sections.icons.techIcon
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
    val privateProjectsFile = File(metadata.publicDataBaseDir, "data/other_projects/${metadata.lang}.json")
    val privateProjectsData = parse<PrivateProjects>(privateProjectsFile)
    println()
    return privateProjectsData.render { privateProjects ->
        Column(
                children = listOf(
                        listOf(sectionText(translations.getOrError("otherProjects"))),
                        privateProjects.projects.mapIndexed { index, it ->
                            Padding(child =
                            Column(children = listOf(
                                    Text(it.title, font = defaultBoldFont),
                                    Row(children = it.skills.map { skillName ->
                                        Skills.valueOf(skillName.toUpperCase())
                                    }.map { skill -> techIcon(skill) },
                                            weights = (0..it.skills.size - 2)
                                                    .map { 0.075f } + (1f - 0.075f * (it.skills.size - 2))
                                    ),
                                    MarkdownRenderer(it.description),
                                    Text(it.repositoryUrl, font = captionFont)
                            )),
                                    padding = Dimensions(top = if (index > 0) 16f else 0f)
                            )
                        }
                ).flatten()
        )

    }
}