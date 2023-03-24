import 'package:jaspr/jaspr.dart';

class Div extends DomComponent {
  Div({
    Component? child,
    List<Component>? children,
    List<String>? classes,
    String? id,
    Styles? styles,
  }) : super(
            tag: 'div',
            child: child,
            children: children,
            classes: classes,
            id: id,
            styles: styles);
}
