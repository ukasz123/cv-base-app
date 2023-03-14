import 'package:jaspr/browser.dart';
import 'package:cv_app_base/app.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

void main() {
  print('main from web!');
  runApp(
    ProviderScope(
      child: Builder.single(builder: (_) => App()),
    ),
  );
}
