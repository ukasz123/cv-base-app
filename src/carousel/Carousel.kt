package carousel

import react.RBuilder
import react.ReactElement
import react.dom.div

fun RBuilder.carousel(classes: String? = null, id: String? = null, items: Sequence<RBuilder.()->ReactElement> = emptySequence()){
    div(classes = "carousel carousel-slider center "+(classes?: "")) {
        id.let {
            this.setProp("id", it)
        }
        this.setProp("data-indicators", "true")
        items.forEach {
            div("carousel-item"){
                it()
            }
        }
    }
}

