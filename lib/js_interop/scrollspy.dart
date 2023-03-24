import 'dart:async';

import 'package:jaspr/html.dart';

import 'src/scrollspy.dart';

class Scrollspy extends StatefulComponent {
  final String id;
  final Component child;

  Scrollspy({
    required this.id,
    required this.child,
  });

  @override
  State<Scrollspy> createState() => _ScrollspyState();
}

class _ScrollspyState extends State<Scrollspy> {
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    scheduleMicrotask(() {
      scrollspyInit(component.id);
    });
  }

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield div(
      [component.child],
      classes: ['scrollspy'],
      id: component.id,
    );
  }
}
