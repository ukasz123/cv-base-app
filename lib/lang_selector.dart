import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class LangSelector extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    var widthClass = "m2";
    final supportedLanguagesCount = SupportedLanguages.values.length;
    if (supportedLanguagesCount % 4 == 0) {
      widthClass = "m3";
    } else if (supportedLanguagesCount % 3 == 0) {
      widthClass = "m4";
    } else if (supportedLanguagesCount % 2 == 0) {
      widthClass = "m6";
    } else if (supportedLanguagesCount == 1) {
      widthClass = "m12";
    }
    final currentSelectedLanguage = context.watch(selectedLanguageProvider);
    for (var element in SupportedLanguages.values) {
      yield span([
        img(
            src: 'public/images/flag_${element.name}.png',
            classes:
                currentSelectedLanguage == element ? ['lang-selected'] : null),
      ], events: {
        'click': (_) {
          context.read(selectedLanguageProvider.notifier).state = element;
        },
      }, classes: [
        widthClass,
        'col',
        'center-align',
        'white-text',
        'pointer',
      ]);
    }
  }
}
