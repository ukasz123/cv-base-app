import 'package:cv_app_base/lang_selector.dart';
import 'package:cv_app_base/pdf_component.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';

class NavigationPanel extends StatelessComponent {
  final List<String> anchors;
  final List<MapEntry<String, void Function()>> languageOptions;
  final SupportedLanguages selectedLang;

  NavigationPanel({
    required this.anchors,
    required this.languageOptions,
    required this.selectedLang,
  });

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
        tag: 'nav',
        child: DomComponent(tag: 'ul', children: [
          li([
            a([
              h1([Text(context.i18n('cvTitle'))])
            ], href: '#${anchors.first}'),
            div(
              [LangSelector()],
              classes: ['lang-selector', 'middle'],
            ),
          ]),
          ...anchors.skip(1).map((e) => li([
                a(
                  [text(context.i18n(e))],
                  href: '#$e',
                )
              ])),
          // pdf link
          li([PdfCVLinkComponent()]),
          li(
            [LangSelector()],
            classes: ['lang-selector', 'tail'],
          ),
        ]));
  }
}
