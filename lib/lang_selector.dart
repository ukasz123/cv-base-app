import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class LangSelector extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    final currentSelectedLanguage = context.watch(selectedLanguageProvider);
    yield* [
      ...SupportedLanguages.values.map(
        (e) => span([
          img(
              src: 'public/images/flag_${e.name}.png',
              classes: currentSelectedLanguage == e ? ['lang-selected'] : null,
              events: {
                'click': (_) {
                  context.read(selectedLanguageProvider.notifier).state = e;
                },
              })
        ]),
      )
    ];
  }
}
