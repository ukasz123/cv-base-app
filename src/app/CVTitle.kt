package app

import i18n.Translator
import react.*
import react.dom.*

inline fun RBuilder.CVTitle(){
    h5("brand-logo white-text"){
        +Translator.getTranslation("cvTitle")
    }
}