package sections.common

import pl.ukaszapps.itext.nodes.Empty
import pl.ukaszapps.itext.nodes.Node

inline fun <reified T> T?.render(renderBody: (T)-> Node): Node = this?.let(renderBody) ?: Empty