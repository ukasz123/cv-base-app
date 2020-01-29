package contactdata

import app.*
import i18n.Translator
import react.*
import react.dom.*

external interface ContactData {
    val name: String
    val address: String
    val linkedin: String
    val github: String
    val bitbucket: String
    val phone: String?
    val email: String?
}

interface ContactDataState : RState {
    var data: ContactData?
}

class ContactDataComponent : RComponent<LanguageState, ContactDataState>() {

    override fun ContactDataState.init(props: LanguageState) {
        state.data = null
    }

    override fun componentWillReceiveProps(nextProps: LanguageState) {
        fetchDataFromJsonFile<ContactData>("data/contactdata/${nextProps.selectedLanguage}.json") {
            setState { data = it }
        }
    }

    override fun RBuilder.render() {
        contentSection(classes = "teal darken-3", id = "contactSection") {
            contentTitle(Translator.getTranslation("contactData"))
            state.data?.let {
                ul("collection") {
                    listItem(
                            iconGen = {
                                img(classes = "circle responsive-img", src = "images/profile_photo.jpg") {}
                            },
                            title = Translator.getTranslation("nameAndSurname"),
                            contentGen = { p { +it.name } }
                    )

                    simpleListItem("place", Translator.getTranslation("address"), {
                        p {
                            +it.address
                        }
                    }, "indigo")
                    it.email?.let { email ->
                        simpleListItem("contact_mail", Translator.getTranslation("email"), {
                            p {
                                +email
                            }
                        }, "purple darken-2")
                    }
                    it.phone?.let { phone ->
                        simpleListItem("contact_phone", Translator.getTranslation("phone"), {
                            p {
                                +phone
                            }
                        }, "amber darken-4")
                    }
                    simpleListItem("public", Translator.getTranslation("social"), {
                        div("row") {
                            div("col s4") {
                                a(href = it.linkedin, classes = "waves-effect waves-lime btn-flat") {
                                    i("small social-linkedin") {}

                                    span("no-text-transform hide-on-small-only") {
                                        +" LinkedIn"
                                    }
                                }
                            }
                            div("col s4") {
                                a(href = it.github, classes = "waves-effect waves-lime btn-flat") {
                                    i("small social-github") {}
                                    span("no-text-transform hide-on-small-only") {
                                        +" GitHub"
                                    }
                                }
                            }
                            div("col s4") {
                                a(href = it.bitbucket, classes = "waves-effect waves-lime btn-flat") {
                                    i("small social-bitbucket") {}
                                    span("no-text-transform hide-on-small-only") {
                                        +" Bitbucket"
                                    }
                                }
                            }
                        }
                    }, "lime darken-1")
                }
            }
        }
    }
}

fun RBuilder.contactData(selectedLanguage: String) {
    child(ContactDataComponent::class) {
        attrs.selectedLanguage = selectedLanguage
    }
}

private fun RBuilder.simpleListItem(icon: String, title: String, contentGen: RBuilder.() -> ReactElement, backgroundColorStyle: String = "blue") =
        listItem(
                iconGen = { i("material-icons circle darken-2 " + backgroundColorStyle) { +icon } },
                title = title,
                contentGen = contentGen,
                backgroundColorStyle = backgroundColorStyle
        )

private fun RBuilder.listItem(iconGen: RBuilder.() -> ReactElement, title: String, contentGen: RBuilder.() -> ReactElement, backgroundColorStyle: String = "blue") {
    li("collection-item avatar") {
        iconGen()
        span("title") { +title }
        contentGen()
    }
}