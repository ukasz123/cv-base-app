import 'package:jaspr/html.dart';

class ContentSection extends StatelessComponent {
  final String? id;
  final String? title;
  final Component child;

  ContentSection(
      {this.id, List<String>? classes, this.title, required this.child});

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield section(
      [
        if (title != null) h1([Text(title!)]),
        child,
      ],
      id: id,
    );
  }
}
