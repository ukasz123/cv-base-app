package projects

import app.LanguageState
import app.contentSection
import app.contentTitle
import app.fetchDataFromJsonFile
import i18n.Translator
import kotlinx.html.DIV
import kotlinx.html.I
import kotlinx.html.Tag
import languages.ForeignLanguagesState
import languages.LanguageData
import react.*
import react.dom.*
import skills.forCode

enum class DeploymentType {
    WEBSITE,
    GOOGLE_PLAY,
    APPLE_APPSTORE;

    companion object  {
        internal fun forName(name: String) = DeploymentType.valueOf(name.toUpperCase())
    }
}

data class Deployment (val type: String, val url: String)

data class Project(
        val title: String,
        val description: String,
        val repositoryUrl: String,
        val skills: Array<String>,
        val deploymentUrls: Array<Deployment>?,
        val imageUrl: String?
)

data class OtherProjects(
        val projects: Array<Project>
)

interface ProjectsGridState : RState {
    var projects: OtherProjects
}

class ProjectsGrid : RComponent<LanguageState, ProjectsGridState>() {


    override fun ProjectsGridState.init() {
        projects = OtherProjects(emptyArray())
    }

    override fun componentWillReceiveProps(nextProps: LanguageState) {
        fetchDataFromJsonFile<OtherProjects>("data/other_projects/${nextProps.selectedLanguage}.json") {
            setState { projects = it }
        }
    }

    override fun RBuilder.render() {
        contentSection(
                id = "otherProjects",
                classes = "amber darken-1"
        ) {
            contentTitle(Translator.getTranslation("otherProjects"))
            div(classes = "row") {
                state.projects.projects.forEachIndexed { index, project ->
                    buildProjectCard(project)
                }
            }
        }
    }
}

private fun <T : Tag> RDOMBuilder<T>.buildProjectCard(project: Project) =
        div(classes = "col m12 l6") {
            div(classes = "card") {
                if (project.imageUrl.isNullOrBlank()) {
                    buildSimpleCard(project)
                } else {
                    buildRichCard(project)
                }
            }
        }

private fun <T : Tag> RDOMBuilder<T>.buildSimpleCard(project: Project) {
    div(classes = "card-content") {
        span(classes = "card-title") { +project.title }
        +project.description
        buildSkills(project.skills)

        div(classes = "card-action") {
            a(href = project.repositoryUrl, target = "_blank") {
                +Translator.getTranslation("repositoryUrl")
            }
            project.deploymentUrls?.let {
                generateActionsForDeployments(it, btnClass="btn-flat waves-amber")
            }
        }
    }
}

private fun <T : Tag> RDOMBuilder<T>.buildSkills(skills: Array<String>) {
    div(classes = "section") {
        span("techs ") {
            skills.mapNotNull { forCode(it) }.forEach {
                b("icon-tech-${it.code}") {
                    setProp("title", it.title)
                }
            }
        }
    }
}

private fun <T : Tag> RDOMBuilder<T>.buildRichCard(project: Project) {
    div(classes = "card-image") {
        img(src = project.imageUrl) {

        }
        a(href = project.repositoryUrl, target = "_blank", classes = "btn-floating btn-large halfway-fab amber accent-2") {
            i(classes = "material-icons") {
                +"folder"
            }
        }
    }
    div(classes = "card-content") {
        span(classes = "card-title") { +project.title }
        +project.description
        buildSkills(project.skills)
        project.deploymentUrls?.let {
            div(classes = "card-action", block = {generateActionsForDeployments(it)})
        }
    }
};

private fun RDOMBuilder<DIV>.generateActionsForDeployments(it: Array<Deployment>, btnClass: String = "btn waves-light amber"): Unit {
    fun updateJsStyle(): RDOMBuilder<I>.() -> Unit {
        return {
            attrs {
                this.jsStyle = kotlinext.js.js {
                    fontSize = "1.5rem"
                }
            }
        }
    }

    it.forEach {
        a(href = it.url, target = "_blank", classes = "waves-effect accent-2 $btnClass") {
            when (DeploymentType.forName(it.type)) {
                DeploymentType.GOOGLE_PLAY ->
                    i(classes = "fab fa-google-play", block = updateJsStyle())
                DeploymentType.APPLE_APPSTORE ->
                    i(classes = "fab fa-app-store", block = updateJsStyle())
                DeploymentType.WEBSITE ->
                    i(classes = "material-icons") {
                        apply ( updateJsStyle() )
                        +"public"
                    }
            }
        }
    }
}


fun RBuilder.otherProjects(selectedLanguage: String) = child(ProjectsGrid::class) {
    attrs.selectedLanguage = selectedLanguage
}