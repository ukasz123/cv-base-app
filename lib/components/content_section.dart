import 'package:cv_app_base/components/content_padding.dart';
import 'package:cv_app_base/components/div.dart';
import 'package:jaspr/jaspr.dart';

class ContentSection extends StatelessComponent {
  final String? id;
  final List<String> _classes;
  final Component child;

  ContentSection({this.id, List<String>? classes, required this.child})
      : _classes = classes ?? [];

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield Div(
      classes: ['scrollspy', 'fullHeight', 'valign-wrapper', ..._classes],
      id: id,
      child: ContentPadding(child),
    );
  }
}
