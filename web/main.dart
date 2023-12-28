import 'package:jaspr/browser.dart';
import 'package:cv_app_base/app.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

void main() {
  runApp(
    ProviderScope(
      child: App(),
    ),
  );
}
