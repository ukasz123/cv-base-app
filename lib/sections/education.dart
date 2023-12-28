import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/providers/education.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class Education extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
        id: 'education',
        title: context.i18n('education'),
        classes: ['purple', 'darken-4'],
        child: Builder(builder: (context) sync* {
          yield ol(
            context.watch(educationProvider).maybeWhen(
                  orElse: () => [],
                  data: (data) => data.collegeStudies
                      .map((e) => li([
                            p([
                              em([text(e.title)])
                            ]),
                            p([text(e.period)]),
                          ]))
                      .toList(growable: false),
                ),
          );
        }));
  }
}
