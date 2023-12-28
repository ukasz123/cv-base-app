import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/technologies_list.dart';
import 'package:cv_app_base/models/other_projects.dart';
import 'package:cv_app_base/providers/other_projects.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class OtherProjects extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      id: 'otherProjects',
      title: context.i18n('otherProjects'),
      child: Builder(
        builder: (context) => context.watch(otherProjectsProvider).maybeWhen(
              orElse: () => [],
              data: (data) => data.projects
                  .map((project) => _ProjectArticle(project))
                  .toList(),
            ),
      ),
    );
  }
}

class _ProjectArticle extends StatelessComponent {
  final Project project;

  _ProjectArticle(this.project);

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield article([
      if (project.imageUrl?.isNotEmpty ?? false) img(src: project.imageUrl!, attributes: {'alt': project.title}),
      div([
        header([
          h2([text(project.title)])
        ]),
        summary([
          p([text(project.description)]),
          TechnologiesList(project.skills),
        ]),
        footer([
          ul(
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
            ].map((e) => li([e])).toList(growable: false),
          ),
        ]),
      ]),
    ]);
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
