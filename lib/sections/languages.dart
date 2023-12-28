import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/providers/languages.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class ForeignLanguages extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      id: 'foreignLanguages',
      title: context.i18n('foreignLanguages'),
      child: Builder(builder: (context) sync* {
        yield ul(context.watch(languagesProvider).maybeWhen(
              orElse: () => [],
              data: (data) => data.known
                  .map((language) => div([
                        header([text(language.name)]),
                        summary([
                          p([text(language.details)]),
                        ])
                      ]))
                  .toList(growable: false),
            ));
      }),
    );
  }
}
