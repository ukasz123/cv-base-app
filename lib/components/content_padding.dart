import 'package:jaspr/jaspr.dart';

class ContentPadding extends StatelessComponent {
  final Component child;
  final List<String>? classes;

  ContentPadding(this.child, [this.classes]);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
      tag: 'div',
      classes: ['container', 'section', ...(classes ?? [])],
      child: DomComponent(
        tag: 'div',
        classes: ['row'],
        child: DomComponent(
          tag: 'div',
          classes: ['col', 's12', 'm9', 'l10', 'offset-m3', 'offset-l2'],
          child: child,
        ),
      ),
    );
  }
}
