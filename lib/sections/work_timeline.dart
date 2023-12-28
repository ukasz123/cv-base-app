import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/markdown.dart';
import 'package:cv_app_base/components/technologies_list.dart';
import 'package:cv_app_base/models/projects.dart';
import 'package:cv_app_base/providers/work_timeline.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class WorkTimeline extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      id: 'workTimeline',
      title: context.i18n('workTimeline'),
      child: Builder(
        builder: (context) sync* {
          yield* context.watch(projectsProvider).maybeWhen(
                orElse: () => [],
                data: (projects) => projects.map((project) => article(
                      [
                        h3([Text(project.name)]),
                        if (project.company != null)
                          h5(
                            [Text(project.company!)],
                          ),
                        _ProjectDescription(project),
                        TechnologiesList(project.skillsUsed),
                        footer([
                          text(
                              '${project.from} - ${project.to ?? context.i18n('now')}')
                        ])
                      ],
                      id: project.code,
                    )),
              );
        },
      ),
    );
  }
}

class _ProjectDescription extends StatelessComponent {
  final Project project;

  _ProjectDescription(this.project);

  @override
  Iterable<Component> build(BuildContext context) {
    final description = context.watch(projectDescriptionProvider(project));
    return [
      if (description.description != null)
        MarkdownComponent(description.description!),
      if (description.urls != null)
        ul(
          description.urls!
              .map((url) => li([
                    a([text(url.title)], href: url.url, target: Target.blank)
                  ]))
              .toList(growable: false),
        )
    ];
  }
}
