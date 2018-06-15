package app

import contactdata.contactData
import education.education
import hobbies.hobbies
import i18n.Translator
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import languages.foreignLanguages
import navigation.navigationPanel
import react.*
import react.dom.*
import skills.skillsData
import timeline.workTimeline
import kotlin.browser.window
import kotlin.js.*

interface LanguageState : RState, RProps {
    var selectedLanguage: String
}

class App : RComponent<RProps, LanguageState>() {
    override fun LanguageState.init() {
        selectedLanguage = "pl"
    }

    private val handleTranslationUpdated = fun(language: String) {
        setState { selectedLanguage = language }
    }

    override fun componentWillMount() {
        Translator.onTranslationUpdated = handleTranslationUpdated;
        Translator.language = state.selectedLanguage
    }

    override fun componentWillUpdate(nextProps: RProps, nextState: LanguageState) {
        Translator.language = nextState.selectedLanguage
    }

    override fun RBuilder.render() {
        div("hide-on-small-only") {
            // left pane
            setProp("id", "tableOfContents")
            div {
                div("col m3 l2") {
                    navigationPanel(
                            arrayOf("contactSection", "skills", "workTimeline", "education", "foreignLanguages", "hobbies"),
                           arrayOf<Pair<String, () -> Unit>>(
                                   Pair("pl", { selectLanguage("pl") }),
                                   Pair("en", { selectLanguage("en") })
                           )
                    ) //FIXME this should point to some real anchors
                }
            }
        }

        nav ("hide-on-med-and-up teal"){
            attrs.id = "languageSelector"
            div("nav-wrapper container") {
                div ("left"){
                    span {
                        +Translator.getTranslation("cvTitle")
                    }
                }
                val targetLang = if (state.selectedLanguage == "pl") "en" else "pl"
                div("right") {
                    div("btn-flat white-text") {
                        +targetLang
                        attrs { onClickFunction = { selectLanguage(targetLang) } }
                    }
                }

            }
        }

        // main pane
        contactData(state.selectedLanguage)
        skillsData(state.selectedLanguage)
        workTimeline(state.selectedLanguage)
        education(state.selectedLanguage)
        foreignLanguages(state.selectedLanguage)
        hobbies(state.selectedLanguage)
        copyrights()
    }

    private fun selectLanguage(langCode: String) {
        setState { selectedLanguage = langCode }
    }
}

fun RBuilder.contentSection(id: String? = null, classes: String? = null, sectionContent: RBuilder.() -> Unit) {
    div("scrollspy fullHeight valign-wrapper " + (classes ?: "")) {
        id?.let {
            this@div.setProp("id", it)
        }
        contentPadding(sectionContent = sectionContent)
    }
}
fun RBuilder.contentPadding(classes: String? = "section", sectionContent: RBuilder.() -> Unit)=
        div("container "+(classes?: "")) {
            div("row") {

                div(classes = "col s12 m9 l10 offset-m3 offset-l2") {
                    sectionContent()
                }
            }}

fun RBuilder.contentTitle(title: String) = h5("white-text") { +title }

fun RBuilder.app() = child(App::class) {}

internal fun fetchDataFromJsonFile(path: String, fulfilled: (Json) -> Unit) {
    window.fetch(path)
            .then({ it.json() })
            .then(
                    {
                        fulfilled(it as Json)
                    },
                    {
                        console.warn("Failed to load data from $path: ${it.toPrettyString()}")
                    }
            )
}

internal fun <T : Any> fetchDataFromJsonFile(path: String, fulfilled: (T) -> Unit) {
    window.fetch(path)
            .then({ it.text() })
            .then(
                    {
                        fulfilled(JSON.parse(it))
                    },
                    {
                        console.warn("Failed to load data from $path: ${it.toPrettyString()}")
                    }
            )
}

internal fun <T : Any?> T.toPrettyString(): String = app.toPrettyString(this)

internal fun <T : Any?> toPrettyString(value: T) =
        if (value == null) "null" else
            "${jsTypeOf(value)}: ${JSON.stringify(value)}"

internal fun RBuilder.parseMarkdown(markdownString: String): ReactElement {

    return div {
        val blocks = markdownString.split('\n')
        var i = 0
        while (i < blocks.size) {
            val paragraph = blocks[i]
            if (paragraph.startsWith(" *")) {
                // add bullet list
                ul ("browser-default"){
                    while (i < blocks.size && blocks[i].startsWith(" *")) {
                        li {
                            +blocks[i].substring(2)
                        }
                        i++
                    }
                }
            } else {
                p {
                    +paragraph
                }
                i++
            }
        }
    }
}