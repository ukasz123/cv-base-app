package sections

import CVMeta
import com.squareup.moshi.JsonClass
import pl.ukaszapps.itext.nodes.*
import sections.common.*
import sections.common.parse
import java.io.File

@JsonClass(generateAdapter = true)
internal data class ContactData(
        var name: String? = null,
        var address: String? = null,
        var linkedin: String? = null,
        var github: String? = null,
        var bitbucket: String? = null,
        var phone: String? = null,
        var email: String? = null
)

internal operator fun ContactData?.plus(other: ContactData?) = other?.let {
    ContactData(
            name = this?.name ?: it.name,
            address = this?.address ?: it.address,
            linkedin = this?.linkedin ?: it.linkedin,
            bitbucket = this?.bitbucket ?: it.bitbucket,
            github = this?.github ?: it.github,
            phone = this?.phone ?: it.phone,
            email = this?.email ?: it.email
    )
} ?: this

fun contactSection(metadata: CVMeta): Node {
    val contactProfilePhotoFile = File(metadata.publicDataBaseDir, "images/profile_photo.jpg")
    val publicContactDataFile = File(metadata.publicDataBaseDir, "data/contactdata/${metadata.lang}.json")
    val privateContactDataFile = File(metadata.privateDataBaseDir, "data/contactdata/${metadata.lang}.json")
    val contactData = parse<ContactData>(publicContactDataFile) + parse<ContactData>(privateContactDataFile)

    val translations = metadata.translations
    return contactData.render {
        var contactSectionChildren: List<Node> = listOf(
                sectionText(translations.getOrError("contactSection")),
                labelText(translations.getOrError("address")),
                defaultText(it.address ?: ""))
        it.email?.let {
            contactSectionChildren += listOf(
                    labelText(translations.getOrError("email")),
                    defaultText(it)
            )
        }
        it.phone?.let {
            contactSectionChildren += listOf(
                    labelText(translations.getOrError("phone")),
                    defaultText(it)
            )
        }
        contactSectionChildren += listOf(

                labelText(translations.getOrError("social")),
                Column(children = listOf(
                        defaultText(it.linkedin ?: ""),
                        defaultText(it.bitbucket ?: ""),
                        defaultText(it.github ?: "")
                )
                )
        )
        Row(children = listOf(
                Column(
                        children = listOf(
                                Image(contactProfilePhotoFile.absolutePath),
                                Align(sectionText(text = it.name!!), alignment = Alignment.CENTER)
                        )
                ),
                Padding(child =
                Column(
                        children = contactSectionChildren
                ),
                        padding = Dimensions(horizontal = 8f))),
                weights = listOf(1f, 1.618f))
    }
}
