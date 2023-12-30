import 'package:jaspr/html.dart';
import 'package:markdown/markdown.dart' as md;

class MarkdownComponent extends StatelessComponent {
  final String markdown;

  MarkdownComponent(this.markdown);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    final document = md.Document(encodeHtml: true);

    final node = document.parse(markdown);
    yield* node.map(_convertNode);
  }
}

Component _convertNode(md.Node n) {
  if (n is md.Element) {
    return _convertElement(n);
  }
  if (n is md.Text) {
    return text(n.text);
  }
  return text('Unknown node: $n');
}

Component _convertElement(md.Element n) {
  final childrenComponents = n.children?.map(_convertNode).toList() ?? [];
  return DomComponent(
      tag: n.tag, children: childrenComponents, attributes: n.attributes);
}
