package hobbies

import app.LanguageState
import app.contentSection
import app.contentTitle
import app.fetchDataFromJsonFile
import i18n.Translator
import react.*
import react.dom.div
import react.dom.img

data class Hobbies (
    val hobbies: Array<Hobby>
)

data class Hobby(val name: String, val iconCode: String)

interface HobbiesState: RState{
    var hobbies: Array<Hobby>
}

class HobbiesComponent: RComponent<LanguageState, HobbiesState>(){

    override fun HobbiesState.init() {
        hobbies = emptyArray()
    }

    override fun componentWillReceiveProps(nextProps: LanguageState) {
        fetchDataFromJsonFile<Hobbies>("data/hobbies/${nextProps.selectedLanguage}.json"){
            setState { hobbies = it.hobbies }
        }
    }

    override fun RBuilder.render() {
        contentSection(
                id = "hobbies",
                classes = "orange darken-1"
        ) {
            contentTitle(Translator.getTranslation("hobbies"))
            div {
                state.hobbies.forEach {
                    div("chip") {
                        img(src = "images/ic_${it.iconCode}.svg"){}
                        +it.name
                    }
                }
            }
        }
    }

}

fun RBuilder.hobbies(selectedLanguage: String) = child(HobbiesComponent::class){
    attrs.selectedLanguage = selectedLanguage
}