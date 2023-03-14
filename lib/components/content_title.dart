import 'package:jaspr/jaspr.dart';

class ContentTitle extends StatelessComponent {
  final String text;

  ContentTitle(this.text);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
      tag: 'h5',
      classes: ['white-text'],
      child: Text(text),
    );
  }
}
