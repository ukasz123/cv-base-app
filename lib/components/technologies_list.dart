import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';

class TechnologiesList extends StatelessComponent {
  final List<String> technologies;

  TechnologiesList(this.technologies);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield h4([text(context.i18n('technologies'))]);
    yield ul(
      technologies
          .map(
            (technology) => li(
              [
                span(
                  [],
                  classes: ['icon-tech-$technology'],
                  attributes: {'title': technology},
                )
              ],
            ),
          )
          .toList(growable: false),
          classes: ['technologies'],
    );
  }
}
