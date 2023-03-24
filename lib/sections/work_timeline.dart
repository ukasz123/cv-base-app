import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/components/markdown.dart';
import 'package:cv_app_base/js_interop/collapsible.dart';
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
      classes: ['bg-coral'],
      child: Builder(builder: (context) sync* {
        yield ContentTitle(context.i18n('projects'));
        yield Collapsible(
          tag: 'ul',
          id: 'collapsibleTimeline',
          child: Builder(
            builder: (context) => context.watch(projectsProvider).maybeWhen(
                  orElse: () => [],
                  data: (data) => data
                      .map(
                        (project) => Builder.single(
                          builder: (context) {
                            return li(
                              [
                                _ProjectListItemContent(project),
                              ],
                              classes: [
                                'project',
                                // 'project-current',
                              ],
                              id: project.code,
                            );
                          },
                        ),
                      )
                      .toList(growable: false),
                ),
          ),
        );
      }),
    );
  }
}

class _ProjectListItemContent extends StatelessComponent {
  final Project project;

  static const _companyNameStyle =
      Styles.text(fontStyle: FontStyle.italic, fontWeight: FontWeight.w500);

  static const _periodHeaderStyle = Styles.text(
    fontSize: Unit.rem(1.2),
    fontWeight: FontWeight.lighter,
  );

  _ProjectListItemContent(this.project);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    final description = context.watch(projectDescriptionProvider(project));
    yield div([
      div([
        h5([text(description.title ?? project.name)]),
        if (project.company != null)
          span([text(project.company!)], styles: _companyNameStyle),
      ], classes: [
        'col',
        's8',
        'l9'
      ]),
      div([
        span(
            project.skillsUsed
                .map((code) => b(
                      [],
                      classes: ['icon-tech-${code.toLowerCase()}'],
                      attributes: {'title': code},
                    ))
                .toList(growable: false),
            classes: ['techs', 'right-align']),
      ], classes: [
        'col',
        's4',
        'l3',
        'valign-wrapper'
      ])
    ], classes: [
      'collapsible-header'
    ]);

    yield div([
      div(
        [
          h6([text('${project.from} - ${project.to ?? context.i18n('now')}')],
              classes: ['white-text'], styles: _periodHeaderStyle),
          div(
            [
              if (description.description != null)
                MarkdownComponent(description.description!),
              p(
                (description.urls ?? [])
                    .map((url) => a([
                          span([text(url.title)],
                              classes: ['grey-text', 'text-lighten-3'])
                        ], href: url.url, target: Target.blank))
                    .fold<List<Component>>(
                        [],
                        (previousValue, element) =>
                            [...previousValue, element, text(' ')]),
              )
            ],
            classes: ['white-text'],
          ),
        ],
      ),
    ], classes: [
      'collapsible-body'
    ]);
  }
}
