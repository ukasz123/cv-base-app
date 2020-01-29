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
    var email: String? =null
)

fun contactSection(metadata: CVMeta): Node {
    val contactProfilePhotoFile = File(metadata.publicDataBaseDir, "images/profile_photo.jpg")
    val contactDataFile = File(metadata.publicDataBaseDir, "data/contactdata/${metadata.lang}.json")
    val contactData = parse<ContactData>(contactDataFile)
    val translations = metadata.translations
    return contactData.render {
        Row(children = listOf<Node>(
                Column(
                        children = listOf(
                                Image(contactProfilePhotoFile.absolutePath),
                                Align(sectionText(text = it.name!!), alignment = Alignment.CENTER)
                        )
                ),
                Padding(child =
                Column(
                        children = listOf(
                                sectionText(translations.getOrError("contactSection")),
                                labelText(translations.getOrError("address")),
                                defaultText(it.address ?: ""),
                                labelText(translations.getOrError("social")),
                                Column(children = listOf(
                                        defaultText(it.linkedin ?: ""),
                                        defaultText(it.bitbucket ?: ""),
                                        defaultText(it.github ?: "")
                                )
                                )
                        )
                ),
                        padding = Dimensions(horizontal = 8f))),
                weights = listOf(1f, 1.618f))
    }
}
