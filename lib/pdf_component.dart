import 'package:cv_app_base/providers/pdf_access.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class PdfCVLinkComponent extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    final path = context.watch(pdfCVFileProvider);
    yield a(
      [
        span([], classes: [
          'fas',
          'fa-file-pdf'
        ], attributes: {
          'aria-hidden': 'true',
        }),
        span([Text(' ')]),
        span([Text(context.i18n('downloadCV'))]),
      ],
      href: path,
      target: Target.blank,
      classes: ['cta-button'],
    );
  }
}
