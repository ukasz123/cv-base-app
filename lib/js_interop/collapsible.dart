import 'dart:async';

import 'src/collapsible.dart';
import 'package:jaspr/html.dart';

class Collapsible extends StatefulComponent {
  final String id;
  final Component child;
  final String tag;
  final String collapsible;

  Collapsible(
      {required this.id,
      required this.child,
      this.tag = 'div',
      this.collapsible = 'accordion'});

  @override
  State<Collapsible> createState() => _CollapsibleState();
}

class _CollapsibleState extends State<Collapsible> {
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    scheduleMicrotask(() {
      collapsibleInit(component.id);
    });
  }

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
      tag: component.tag,
      child: component.child,
      classes: ['collapsible'],
      attributes: {
        'data-collapsible': component.collapsible,
      },
      id: component.id,
    );
  }
}
