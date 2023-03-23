import 'dart:async';

import 'package:jaspr/html.dart';

import 'src/pushpin.dart';

class PushPin extends StatefulComponent {
  final String id;
  final Component child;
  final List<String>? classes;

  PushPin({required this.id, required this.child, this.classes});

  @override
  State<PushPin> createState() => _PushPinState();
}

class _PushPinState extends State<PushPin> {
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    scheduleMicrotask(() {
      pushpinInit(component.id);
    });
  }

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield div(
      [component.child],
      classes: component.classes,
      id: component.id,
    );
  }
}
