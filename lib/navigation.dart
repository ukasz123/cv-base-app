import 'package:cv_app_base/components/copyrights.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';

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
      tag: 'div',
      classes: ['fullHeight'],
      child: DomComponent(
        tag: 'div',
        classes: ['container', 'push-down'],
        children: [
          div([
            h5(
              [Text('TODO: title')],
              classes: ['brand-logo', 'white-text'],
            ),
          ]),
          Builder(
            builder: (context) sync* {
              yield TableOfContents(anchors);
            },
          ),
          // TODO: pdf link
          // TODO LAng selector
        ],
      ),
    );
  }
}

class TableOfContents extends StatelessComponent {
  final List<String> anchors;

  TableOfContents(this.anchors);

  @override
  Iterable<Component> build(BuildContext context) {
    return [
      DomComponent(
        tag: 'ul',
        classes: ['section', 'table-of-contents'],
        child: Builder(
          builder: (innerContext) => anchors.map(
            (code) => li([
              a(
                [text(innerContext.i18n(code))],
                href: '#$code',
                classes: ['white-text'],
              ),
            ]),
          ),
        ),
      ),
    ];
  }
}
