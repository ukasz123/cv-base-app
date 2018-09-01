package timeline

import app.*
import i18n.Translator
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.*
import react.setState
import skills.forCode


data class History(val projects: Array<Project>)

data class Project(
        val code: String,
        val name: String,
        val skillsUsed: Array<String>,
        val from: String,
        val to: String? = null,
        val company: String? = null,
        private val translatedTitle: String? = null,
        private val translatedDescription: String? = null
) {

    val current = to.isNullOrEmpty()
    val title = translatedTitle ?: name
    val description = translatedDescription ?: ""
}


interface WorkTimelineState : RState {
    var projects: Array<Project>
}

class WorkTimeline : RComponent<LanguageState, WorkTimelineState>() {
    override fun WorkTimelineState.init() {
        projects = emptyArray()
    }

    override fun componentWillMount() {
        fetchDataFromJsonFile<History>("data/history/projects.json") {
            it.projects.sortByDescending { it.to ?: "9999-12" }
            val projectsCopy = it.projects.asSequence()
                    .map {
                        Project(
                                code = it.code,
                                name = it.name,
                                to = it.to,
                                from = it.from,
                                skillsUsed = it.skillsUsed,
                                company = it.company
                        )
                    }.toList().toTypedArray()
            setState { projects = projectsCopy }
            loadTranslatedDetails(projectsCopy, props.selectedLanguage)
        }
    }


    override fun componentWillReceiveProps(nextProps: LanguageState) {
        loadTranslatedDetails(state.projects, nextProps.selectedLanguage)
    }

    private fun loadTranslatedDetails(projects: Array<Project>, selectedLanguage: String) {
        projects.forEachIndexed { index, project ->
            fetchDataFromJsonFile("data/history/${project.code}/$selectedLanguage.json") {
                val translatedTitle = it["title"]
                val translatedDescription = it["description"]
                if (translatedTitle != null || translatedDescription != null) {
                    val projectTranslated = project.copy(
                            translatedTitle = translatedTitle?.toString(),
                            translatedDescription = translatedDescription?.toString()
                    )
                    val nextStateProjects = state.projects.copyOf()
                    nextStateProjects[index] = projectTranslated
                    setState { this.projects = nextStateProjects }
                }
            }
        }
    }

    override fun RBuilder.render() {
        contentSection(id = "workTimeline", classes = "teal darkem-1") {
            contentTitle(Translator.getTranslation("projects"))

            ul("collapsible") {
                setProp("data-collapsible", "accordion")
                state.projects.map { it.copy() }.forEach {
                    li("project " + (if (it.current) "project-current" else "")) {
                        setProp("id", it.code)
                        div("collapsible-header") {
                            div("col s8 l9") {
                                h5{+it.title}
                                it.company?.let {
                                    span("Project-company-name") {
                                        +it
                                    }
                                }
                            }
                            div("col s4 l3 valign-wrapper") {
                                span("techs right-align") {
                                    it.skillsUsed.mapNotNull { forCode(it) }.forEach {
                                        b("icon-tech-${it.code}") {
                                            setProp("title", it.title)
                                        }
                                    }

                                }
                            }

                        }
                        div("collapsible-body") {
                            h6("white-text Project-period-header") {
                                +"${it.from} - ${it.to ?: Translator.getTranslation("now")}"
                            }
                            div("white-text") {
                                parseMarkdown(it.description)
                            }
                        }
                    }
                }
            }
        }
    }

}


fun RBuilder.workTimeline(selectedLanguage: String) = child(WorkTimeline::class) {
    attrs.selectedLanguage = selectedLanguage
}