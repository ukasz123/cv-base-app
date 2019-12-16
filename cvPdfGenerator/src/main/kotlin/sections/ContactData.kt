package sections

import CVMeta
import com.squareup.moshi.JsonClass
import pl.ukaszapps.itext.nodes.*
import java.io.File

@JsonClass(generateAdapter = true)
internal data class ContactData(
    var name: String? = null,
    var address: String? = null,
    var linkedin: String? = null,
    var github: String? = null,
    var bitbucket: String? = null
)

fun contactSection(metadata: CVMeta): Node {
  val contactProfilePhotoFile = File(metadata.baseDir, "images/profile_photo.jpg")
  val contactDataFile = File(metadata.baseDir, "data/contactdata/${metadata.lang}.json")
  val contactData = parse<ContactData>(contactDataFile)
  val translations = metadata.translations
  return Row(children = listOf<Node>(
      Column(
          children = listOf(
              Image(contactProfilePhotoFile.absolutePath),
              Align(sectionText(text = contactData.name!!), alignment = Alignment.CENTER)
          )
      ),
      Padding(child =
      Column(
          children = listOf(
              sectionText(translations.getOrError("contactSection")),
              labelText(translations.getOrError("address")),
              defaultText(contactData.address ?: ""),
              labelText(translations.getOrError("social")),
              Column(children = listOf(
                  defaultText(contactData.linkedin ?: ""),
                  defaultText(contactData.bitbucket ?: ""),
                  defaultText(contactData.github ?: "")
              )
              )
          )
      ),
          padding = Dimensions(horizontal = 8f))),
      weights = listOf(0.3f, 0.7f))
}