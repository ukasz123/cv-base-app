import 'dart:async';

import 'src/collapsible.dart';
import 'package:jaspr/html.dart';

class Collapsible extends StatefulComponent {
  final String id;
  final Component child;

  Collapsible({
    required this.id,
    required this.child,
  });

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
    yield div(
      [component.child],
      classes: ['collapsible'],
      id: component.id,
    );
  }
}
