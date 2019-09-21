package navigation

import i18n.Translator
import react.RBuilder
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import app.CVTitle

fun RBuilder.navigationPanel(anchors: Array<String>, langOptions: Array<Pair<String, ()->Unit>>, selectedLang: String) {
    div ("fullHeight"){
        div("container push-down") {
            div {
                CVTitle()
            }
            ul(classes = "section table-of-contents") {
                anchors.forEach {sectionCode ->
                    li {
                        a(href = "#$sectionCode", classes = "white-text") {
                            +Translator.getTranslation(sectionCode)
                            attrs { onClickFunction = { Gtag.sendNavigationEvent(
                                    viewName = sectionCode,
                                    click = true
                            ) } }
                        }
                    }
                }
            }

            // language options
            div("row") {
                var widthStyle = "m2"
                if (langOptions.size % 4 == 0) {
                    widthStyle = "m3"
                } else if (langOptions.size % 3 == 0){
                    widthStyle = "m4"
                } else if (langOptions.size % 2 == 0) {
                    widthStyle = "m6"
                } else if (langOptions.size == 1){
                    widthStyle = "m12"
                }

                langOptions.forEach {

                    val langData = it
                    span("col $widthStyle center-align white-text pointer") {
                        img(src = "images/flag_${langData.first}.png", classes = if (langData.first == selectedLang) "lang-selected" else ""){

                        }
                        attrs { onClickFunction = { langData.second() } }
                    }

                }
            }
        }
    }
}

fun spyScroll(sectionCode: String){
    Gtag.sendNavigationEvent(
            viewName = sectionCode
    )
}