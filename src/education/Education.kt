package education

import app.LanguageState
import app.contentSection
import app.contentTitle
import app.fetchDataFromJsonFile
import i18n.Translator
import react.*
import react.dom.li
import react.dom.p
import react.dom.ul

data class EducationData(
        val college_studies: Array<EducationInfo>,
        val courses: Array<String> = emptyArray()//TODO to be fixed/changed when some courses happen to be added
)

data class EducationInfo (
        val title: String,
        val period: String
)

interface EducationState : RState {
    var data: EducationData
}

class Education : RComponent<LanguageState, EducationState>() {

    override fun EducationState.init() {
        data = EducationData(emptyArray())
    }

    override fun componentWillReceiveProps(nextProps: LanguageState) {
        fetchDataFromJsonFile<EducationData>("data/education/${nextProps.selectedLanguage}.json"){
            setState { data = it }
        }
    }

    override fun RBuilder.render() {
        contentSection(id = "education", classes = "purple darken-4"){
            contentTitle(Translator.getTranslation("education"))
            ul("collection"){
                state.data.college_studies.forEach {
                    li("collection-item"){
                        p("cv-title"){
                            +it.title
                        }
                        p("text-small"){
                            +it.period
                        }
                    }
                }
            }
        }
    }

}

fun RBuilder.education(selectedLanguage: String) = child(Education::class){
    attrs.selectedLanguage = selectedLanguage
}