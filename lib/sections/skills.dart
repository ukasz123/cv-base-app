import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/js_interop/scrollspy.dart';
import 'package:cv_app_base/providers/skills_data.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class Skills extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield Scrollspy(
      id: 'skills',
      child: ContentSection(
        classes: ['bg-gravel'],
        child: Builder(
          builder: ((context) sync* {
            yield ContentTitle(context.i18n('skills'));
            const skillTextClasses = ['center', 'white-text'];
            yield* context.watch(skillsProvider).maybeWhen(
                orElse: () => [],
                data: (skillsRows) sync* {
                  for (var skillsRow in skillsRows) {
                    final colSize = 12 / skillsRow.length;
                    final colClasses = ['col', 's$colSize'];

                    yield div(
                        skillsRow.map((skill) {
                          final primary = skill.primary;
                          return div([
                            Builder(
                              builder: ((context) sync* {
                                if (skill.icon != null) {
                                  final iconComponent = i(
                                    [],
                                    classes: ['icon-tech-${skill.icon}'],
                                  );
                                  if (primary) {
                                    yield p([iconComponent],
                                        classes: [...skillTextClasses, 'h2']);
                                  } else {
                                    yield h3([iconComponent],
                                        classes: skillTextClasses);
                                  }
                                }

                                if (primary) {
                                  yield h3([text(skill.name)],
                                      classes: skillTextClasses);
                                } else {
                                  yield h5([text(skill.name)],
                                      classes: skillTextClasses);
                                }
                                yield p(
                                  [text('${skill.level}/10')],
                                  classes: skillTextClasses,
                                );
                              }),
                            )
                          ], classes: colClasses);
                        }).toList(growable: false),
                        classes: ['row valign-wrapper']);
                  }
                });
          }),
        ),
      ),
    );
  }
}
