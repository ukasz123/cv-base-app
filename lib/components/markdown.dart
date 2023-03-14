import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';

// TODO: use actually valid markdown parser
class MarkdownComponent extends StatelessComponent {
  final String markdown;

  MarkdownComponent(this.markdown);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield div(
      [
        Builder(builder: (context) sync* {
          final blocks = markdown.split('\n');
          var i = 0;
          while (i < blocks.length) {
            final line = blocks[i];
            if (line.startsWith(' *')) {
              yield ul([
                Builder(builder: (context) sync* {
                  while (i < blocks.length && blocks[i].startsWith(' *')) {
                    yield li([text(blocks[i].substring(2))]);
                    i++;
                  }
                })
              ], classes: [
                'browser-default'
              ]);
            } else {
              yield p([text(line)]);
              i++;
            }
          }
        })
      ],
    );
  }
}
