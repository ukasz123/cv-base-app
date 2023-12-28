import 'package:jaspr/html.dart';

class Copyrights extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield footer(
      [
        span([text('Created by Łukasz Huculak')]),
      ],
      id: 'Copyrights',
    );
  }
}
