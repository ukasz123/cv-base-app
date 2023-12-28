import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/providers/contact_data.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class ContactData extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
      child: Builder(
        builder: (context) sync* {
          final contactData = context.watch(contactDataProvider);
          yield div(
            contactData.when(
              data: (data) => [
                img(src: 'public/images/profile_photo.jpg',
                        classes: [
                          'circle',
                          'responsive-img',
                        ],),
                        ul([
                          _ListItem(
                            icon: 'person',
                            title: span([Text(context.i18n('nameAndSurname'))]),
                            content: span([Text(data.name)]),
                          ),
                          _ListItem(
                            icon: 'place',
                            title: span([Text(context.i18n('address'))]),
                            content: span([Text(data.address)]),
                          ),
                          _ListItem(
                            icon: 'public',
                            title: span([Text(context.i18n('social'))]),
                            content: 
                            div([
                              _SocialRow(href: data.linkedin, name: 'LinkedIn', icon: 'social-linkedin'),
                              _SocialRow(href: data.github, name: 'GitHub', icon: 'social-github'),
                              _SocialRow(href: data.bitbucket, name: 'Bitbucket', icon: 'social-bitbucket'),
                            ])
                            ,
                          ),
                        ])
              ],
              error: (error, stackTrace) => [Text('Error: $error')],
              loading: () => [],
            ),
        );
        },
      ),
      id: 'contactData',
      title: context.i18n('contactData'),
    );
    
  }
}

class _ListItem extends StatelessComponent {
  final String icon;
  final Component title;
  final Component content;

  _ListItem({required this.icon, required this.title, required this.content});

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield li([
      span([Text(icon)], classes: ['material-icons', 'circle']),
      DomComponent(
        tag: 'dl',
        children: [
         DomComponent(tag: 'dt', child: title),
          DomComponent(tag: 'dd',child: content),
        ]),
    ]);
  }
}

class _SocialRow extends StatelessComponent {

  final String href;
  final String name;
  final String icon;

  _SocialRow({ required this.href, required this.name, required this.icon});

  @override
  Iterable<Component> build(BuildContext context) sync*{
    yield div([
      a([
        i([], classes: ['small', icon]),
        span([Text(name)], classes: ['no-text-transform', 'hide-on-small-only',]),
      ], href: href),
    ], classes: ['col', 's4']);
  }

}
