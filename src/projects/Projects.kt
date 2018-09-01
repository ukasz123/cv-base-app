package projects

import app.LanguageState
import app.contentSection
import app.contentTitle
import i18n.Translator
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class ProjectsGrid : RComponent<LanguageState, RState>() {
    override fun RBuilder.render() {
        contentSection(
                id = "otherProjects",
                classes = "amber darken-1"
        ) {
            contentTitle(Translator.getTranslation("otherProjects"))

        }
    }
}

fun RBuilder.otherProjects(selectedLanguage: String) = child(ProjectsGrid::class){
    attrs.selectedLanguage = selectedLanguage
}