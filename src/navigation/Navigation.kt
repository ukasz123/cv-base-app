package navigation

import i18n.Translator
import react.RBuilder
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import app.CVTitle

fun RBuilder.navigationPanel(anchors: Array<String>, langOptions: Array<Pair<String, ()->Unit>>) {
    div ("fullHeight"){
        div("container push-down") {
            div {
                CVTitle()
            }
            ul(classes = "section table-of-contents") {
                anchors.forEach {
                    li {
                        a(href = "#$it", classes = "white-text") {
                            +Translator.getTranslation(it)
                        }
                    }
                }
            }

            // language options
            div("row") {
                langOptions.forEach {

                    val langData = it
                    span("col m2 center-align white-text pointer") {
                        +langData.first
                        attrs { onClickFunction = { langData.second() } }
                    }

                }
            }
        }
    }
}