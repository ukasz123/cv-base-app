import 'package:cv_app_base/components/copyrights.dart';
import 'package:cv_app_base/providers/pdf_access.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

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
              [Text(context.i18n('cvTitle'))],
              classes: ['brand-logo', 'white-text'],
            ),
          ]),
          Builder(
            builder: (context) sync* {
              yield TableOfContents(anchors);
            },
          ),

          //  pdf link
          div([
            div([_PdfCVLinkComponent()], classes: ['center-align']),
          ], classes: [
            'row'
          ]),
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

class _PdfCVLinkComponent extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    final path = context.watch(pdfCVFileProvider);
    yield a([
      img(
          src:
              'https://upload.wikimedia.org/wikipedia/commons/8/87/PDF_file_icon.svg',
          classes: ['pdf-icon', 'center-align']),
    ], href: path, target: Target.blank);
  }
}
