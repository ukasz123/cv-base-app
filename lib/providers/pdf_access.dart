import 'package:cv_app_base/translation.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

final pdfCVFileProvider = Provider<String>((ref) =>
    'public/generated/cv-${ref.watch(selectedLanguageProvider).name}.pdf');
