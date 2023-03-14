import 'package:jaspr/server.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';
import './app.dart';

void main() {
  print('main from lib!');
  runApp(Document.file(
    name: 'index.html',
    attachTo: 'body',
    child: ProviderScope(child:  App()),
  ));
}
