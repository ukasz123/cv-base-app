import 'package:jaspr/server.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';
import './app.dart';

void main() {
  runApp(Document.file(
    name: 'index.html',
    attachTo: 'body',
    child: ProviderScope(child:  App()),
  ));
}
