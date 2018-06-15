package languages

import app.*
import i18n.Translator
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.div
import react.dom.p
import react.dom.span
import react.setState

data class LanguageData(
        //NOTE: it has to be array - JSON arrays aren't parsed to lists
		val known: Array<Language>
)

data class Language(
		val name: String,
		val details: String
)
interface ForeignLanguagesState : RState {
    var languageData: LanguageData
}

class ForeignLanguages: RComponent<LanguageState, ForeignLanguagesState>(){

    private val colorsPalette = arrayOf("brown accent-3", "pink darken-3", "grey darken-2")
    private val colorsPaletteSize = colorsPalette.size

    override fun ForeignLanguagesState.init() {
        languageData = LanguageData(emptyArray())
    }
    override fun componentWillReceiveProps(nextProps: LanguageState) {
        fetchDataFromJsonFile<LanguageData>("data/foreign_languages/${nextProps.selectedLanguage}.json") {
            setState { languageData = it }
        }
    }

    override fun RBuilder.render() {
        contentSection(
                id = "foreignLanguages",
                classes = "teal darken-1"
        ){
            contentTitle(Translator.getTranslation("foreignLanguages"))
            state.languageData?.known.forEachIndexed { index, language ->
                val colorClass = colorsPalette[index % colorsPaletteSize]
                div("card $colorClass") {
                    div("card-content white-text") {
                        span("card-title") {
                            +language.name
                        }
                        p {
                            +language.details
                        }
                    }
                }
            }
        }
    }

}

fun RBuilder.foreignLanguages(selectedLanguage: String) = child(ForeignLanguages::class){
    attrs.selectedLanguage = selectedLanguage
}