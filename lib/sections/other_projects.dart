import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/models/other_projects.dart';
import 'package:cv_app_base/providers/other_projects.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class OtherProjects extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
        id: 'otherProjects',
        classes: ['amber', 'darken-1'],
        child: Builder(builder: (context) sync* {
          yield ContentTitle(context.i18n('otherProjects'));
          yield div(
              context.watch(otherProjectsProvider).maybeWhen(
                    orElse: () => [],
                    data: (data) => data.projects
                        .map((project) => _ProjectCard(project: project))
                        .map((card) => div([
                              div([card], classes: ['card'])
                            ], classes: [
                              'col',
                              'm12',
                              'l6'
                            ]))
                        .toList(),
                  ),
              classes: ['row']);
        }));
  }
}

class _ProjectCard extends StatelessComponent {
  final Project project;

  _ProjectCard({required this.project});
  @override
  Iterable<Component> build(BuildContext context) sync* {
    if (project.imageUrl?.isEmpty ?? true) {
      yield _SimpleProjectCard(project);
    } else {
      yield _RichProjectCard(project);
    }
  }
}

class _RichProjectCard extends StatelessComponent {
  final Project project;

  _RichProjectCard(this.project);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield div([
      img(src: project.imageUrl!),
      a(
        [
          i([text('folder')], classes: ['material-icons'])
        ],
        href: project.repositoryUrl ?? '',
        target: Target.blank,
        classes: [
          'btn-floating',
          'btn-large',
          'halfway-fab',
          'amber',
          'accent-2'
        ],
      )
    ], classes: [
      'card-image'
    ]);

    yield div(
      [
        span([text(project.title)], classes: ['card-title']),
        text(project.description),
        _SkillsUsed(project.skills),
        div(
          project.deploymentUrls
              .map((deploymentUrl) => _DeploymentUrl(deploymentUrl))
              .toList(growable: false),
          classes: ['card-action'],
        ),
      ],
      classes: ['card-content'],
    );
  }
}

class _SimpleProjectCard extends StatelessComponent {
  final Project project;

  _SimpleProjectCard(this.project);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield div([
      span([text(project.title)], classes: ['card-title']),
      text(project.description),
      _SkillsUsed(project.skills),
      div(
        [
          if (project.repositoryUrl != null)
            a(
              [text(context.i18n('repositoryUrl'))],
              href: project.repositoryUrl!,
              target: Target.blank,
            ),
          ...project.deploymentUrls
              .map((deploymentUrl) => _DeploymentUrl(deploymentUrl))
              .toList(growable: false),
        ],
        classes: ['card-action'],
      )
    ], classes: [
      'card-content'
    ]);
  }
}

class _SkillsUsed extends StatelessComponent {
  final List<String> skills;

  _SkillsUsed(this.skills);

  @override
  Iterable<Component> build(BuildContext context) {
    return [
      div(
        skills
            .map((s) =>
                b([], classes: ['icon-tech-$s'], attributes: {'title': s}))
            .toList(growable: false),
        classes: ['section', 'techs'],
      )
    ];
  }
}

class _DeploymentUrl extends StatelessComponent {
  final DeploymentUrl url;
  final List<String> _classes;

  _DeploymentUrl(this.url, {List<String>? classes})
      : _classes = classes ?? ['btn', 'waves-light', 'amber'];
  @override
  Iterable<Component> build(BuildContext context) sync* {
    var classes = <String>[];
    var style = Styles.text(fontSize: Unit.rem(1.5));
    var icon = <Component>[];
    switch (url.type) {
      case "WEBSITE":
        classes = ['material-icons'];
        icon = [text('public')];
        break;
      case "APPLE_APPSTORE":
        classes = ['fab', 'fa-app-store'];
        break;
      case "GOOGLE_PLAY":
        classes = ['fab', 'fa-google-play'];
        break;
    }
    yield a(
      [
        i(
          icon,
          classes: classes,
          styles: style,
        )
      ],
      href: url.url,
      target: Target.blank,
      classes: [
        'waves-effect',
        'accent-2',
        ..._classes,
      ],
    );
  }
}
