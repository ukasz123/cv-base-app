package app

import react.RBuilder
import react.dom.div
import react.dom.span
// builds Copyrights footer
fun RBuilder.copyrights() =
        div("Copyrights") {
            contentPadding(classes = null) {
                span("white-text") {
                    +"Created by Łukasz Huculak"
                }
            }
        }