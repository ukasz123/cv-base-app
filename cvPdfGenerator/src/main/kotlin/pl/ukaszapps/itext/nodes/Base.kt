package pl.ukaszapps.itext.nodes

import com.itextpdf.text.Element

interface Node {
  fun render(): Element
}

interface Leaf : Node

abstract class SingleChildContainer(val child: Node) : Node

abstract class MultiChildContainer(val children: List<Node>) : Node