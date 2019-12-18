package sections.common

import pl.ukaszapps.itext.nodes.Text

fun sectionText(text: String): Text  = Text(text = text, font = sectionHeaderFont)
fun labelText(text: String): Text  = Text(text = text, font = labelFont)
fun defaultText(text: String): Text  = Text(text = text, font = defaultFont)
