

import 'package:cv_app_base/providers/pdf_access.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class PdfCVLinkComponent extends StatelessComponent {
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
