package sections

import CVMeta
import pl.ukaszapps.itext.nodes.Column
import pl.ukaszapps.itext.nodes.Empty
import pl.ukaszapps.itext.nodes.Node
import pl.ukaszapps.itext.nodes.NodeList
import sections.common.*
import sections.common.getOrError
import sections.common.parse
import java.io.File

data class Hobby(val name: String)
data class HobbyData(val hobbies: List<Hobby>)

fun hobbiesSection(meta: CVMeta): Node {
  val hobbiesDataFile = File(meta.publicDataBaseDir, "data/hobbies/${meta.lang}.json")
  val hobbiesData = parse<HobbyData>(hobbiesDataFile)
    
  return hobbiesData.render {
      Column(
              children = listOf(
                      sectionText(meta.translations.getOrError("hobbies")),
                      NodeList(
                              children = it.hobbies.map {
                                  defaultText(it.name)
                              }
                      )
              )
      )
  } 
}