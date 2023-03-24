import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/models/education.dart';
import 'package:cv_app_base/providers/education.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class Education extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
        id: 'education',
        classes: ['bg-violet'],
        child: Builder(builder: (context) sync* {
          yield ContentTitle(context.i18n('education'));
          yield ul(
              context.watch(educationProvider).maybeWhen(
                    orElse: () => [],
                    data: (data) => data.collegeStudies
                        .map((e) => _StudiesComponent(e))
                        .toList(growable: false),
                  ),
              classes: ['collection']);
        }));
  }
}

class _StudiesComponent extends StatelessComponent {
  final CollegeStudy study;

  _StudiesComponent(this.study);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield li(
      [
        p([text(study.title)], classes: ['cv-title']),
        p([text(study.period)], classes: ['text-small']),
      ],
      classes: ['collection-item'],
    );
  }
}
