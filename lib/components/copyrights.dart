import 'package:cv_app_base/components/content_padding.dart';
import 'package:jaspr/jaspr.dart';

class Copyrights extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
        tag: 'div',
        id: 'Copyrights',
        classes: ['cyan', 'lighten-3'],
        child: ContentPadding(DomComponent(
          tag: 'span',
          child: Text('Created by Łukasz Huculak'),
        )));
  }
}
