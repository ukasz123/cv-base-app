package sections

import CVMeta
import com.squareup.moshi.Json
import pl.ukaszapps.itext.nodes.*
import sections.common.*
import sections.common.defaultBoldFont
import sections.common.defaultFont
import sections.common.getOrError
import sections.common.parse
import java.io.File


class Skill {
  @Json(name = "name")
  var name: String? = null
  @Json(name = "primary")
  var primary: Boolean = false
  @Json(name = "icon")
  var icon: String? = null
  @Json(name = "level")
  var level: Int? = null

}

class SkillsData {
  @Json(name = "skills")
  var skills: List<Skill>? = null
}


fun skillsSection(metadata: CVMeta): Node {
  val skillsDataFile = File(metadata.publicDataBaseDir, "data/skills/skills_data.json")
  val translations = metadata.translations
  val skills = parse<SkillsData>(skillsDataFile)
  return Column(
      children = listOf(
          sectionText(translations.getOrError("skills")),
          NodeList(
              type = NodeListType.NUMBERED,
              children = (skills.skills ?: emptyList()).mapNotNull {
                it.toNode()
              }
          )
      )
  )
}

private fun Skill.toNode(): Node? {
  val font = if (primary){
    defaultBoldFont
  } else {
    defaultFont
  }
  return name?.let {
    return Text("$it: ${level ?: 1}/10", font = font)
  }
}
