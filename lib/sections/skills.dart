import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/models/skills.dart';
import 'package:cv_app_base/providers/skills_data.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class Skills extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      id: 'skills',
      title: context.i18n('skills'),
      child: ul(
        [
          Builder(
            builder: (context) sync* {
              yield* context.watch(skillsProvider).maybeWhen(
                    orElse: () => [],
                    data: (skills) {
                      return skills.map((s) => _SkillItem(
                            skill: s,
                          ));
                    },
                  );
            },
          )
        ],
      ),
    );
  }
}

class _SkillItem extends StatelessComponent {
  final Skill skill;

  _SkillItem({required this.skill});

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield li(
      [
        if (skill.icon != null)
          span([], classes: [
            'icon-tech-${skill.icon}'
          ], attributes: {
            'aria-hidden': 'true',
          }),
        strong([Text(skill.name)])
      ],
      attributes: {
        'data-weight': '${skill.level}',
        'aria-valuenow': '${skill.level}',
        'aria-valuemin': '0',
        'aria-valuemax': '10',
      },
      classes: skill.primary ? ['highlighted'] : [],
    );
  }
}
