import 'package:jaspr/html.dart';

// TODO: use actually valid markdown parser
class MarkdownComponent extends StatelessComponent {
  final String markdown;

  MarkdownComponent(this.markdown);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    final blocks = markdown.split('\n');
    yield* parseMarkdown(blocks);
  }
}

Iterable<Component> parseMarkdown(List<String> input) sync* {
  List<Component>? lastULElements;

  for (var i = 0; i < input.length; i++) {
    final line = input[i];
    if (line.startsWith(' *')) {
      lastULElements ??= [];

      lastULElements.add(li([
        span([text(line.substring(2))])
      ]));
    } else {
      if (lastULElements != null) {
        yield ul(
          lastULElements,
        );
        lastULElements = null;
      }
      yield p([text(line)]);
    }
  }
  if (lastULElements != null) {
    yield ul(
      lastULElements,
    );
    lastULElements = null;
  }
}
