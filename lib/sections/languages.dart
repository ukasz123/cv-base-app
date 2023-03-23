import 'package:collection/collection.dart';
import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/models/foreign_languages.dart';
import 'package:cv_app_base/providers/languages.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class ForeignLanguages extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      id: 'foreignLanguages',
      classes: ['teal', 'darken -1'],
      child: Builder(builder: (context) sync* {
        yield ContentTitle(context.i18n('foreignLanguages'));
        yield ul(context.watch(languagesProvider).maybeWhen(
              orElse: () => [],
              data: (data) => data.known
                  .mapIndexed((index, e) => _LanguageItem(index, e))
                  .toList(growable: false),
            ));
      }),
    );
  }
}

class _LanguageItem extends StatelessComponent {
  final Language _known;
  final int _index;
  static final _colorsPalette = [
    ['brown', 'accent-3'],
    ['pink', 'darken-3'],
    ['grey', 'darken-2'],
  ];

  _LanguageItem(this._index, this._known);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield li([
      div([
        span([text(_known.name)], classes: ['card-title']),
        p([text(_known.details)]),
      ], classes: [
        'card-content',
        'white-text'
      ])
    ], classes: [
      'card',
      ..._colorsPalette[_index % _colorsPalette.length]
    ]);
  }
}
