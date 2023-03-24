import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/components/list_item.dart';
import 'package:cv_app_base/js_interop/scrollspy.dart';
import 'package:cv_app_base/providers/contact_data.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class ContactData extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    final contactData = context.watch(contactDataProvider);

    yield Scrollspy(
      id: 'contactData',
      child: ContentSection(
        classes: ['teal', 'darken-3'],
        child: Builder(
          builder: (context) sync* {
            yield ContentTitle(context.i18n('contactData'));
            yield* contactData.when(
              data: (data) sync* {
                yield DomComponent(
                  tag: 'ul',
                  classes: ['collection'],
                  child: Builder(builder: (context) sync* {
                    yield ListItem(
                      text: context.i18n('nameAndSurname'),
                      icon: img(
                        src: 'public/images/profile_photo.jpg',
                        classes: [
                          'circle',
                          'responsive-img',
                        ],
                      ),
                    );
                    yield SimpleListItem(
                        text: context.i18n('address'),
                        icon: Text('place'),
                        content: p([Text(data.address)]),
                        classes: ['indigo']);
                    yield SimpleListItem(
                      text: context.i18n('social'),
                      icon: Text('public'),
                      classes: ['lime-darken'],
                      content: div(
                        [
                          a(
                            [
                              i([], classes: ['small social-linkedin']),
                              span(
                                [text(" LinkedIn")],
                                classes: [
                                  'no-text-transform hide-on-small-only'
                                ],
                              )
                            ],
                            href: data.linkedin,
                            classes: ['waves-effect', 'waves-lime', 'btn-flat'],
                          ),
                          a(
                            [
                              i([], classes: ['small social-github']),
                              span(
                                [text(" GitHub")],
                                classes: [
                                  'no-text-transform hide-on-small-only'
                                ],
                              )
                            ],
                            href: data.linkedin,
                            classes: ['waves-effect', 'waves-lime', 'btn-flat'],
                          ),
                          a(
                            [
                              i([], classes: ['small social-bitbucket']),
                              span(
                                [text(" Bitbucket")],
                                classes: [
                                  'no-text-transform hide-on-small-only'
                                ],
                              )
                            ],
                            href: data.linkedin,
                            classes: ['waves-effect', 'waves-lime', 'btn-flat'],
                          ),
                        ]
                            .map((component) =>
                                div([component], classes: ['col s4']))
                            .toList(),
                        classes: ['row'],
                      ),
                    );
                  }),
                );
              },
              error: (error, stackTrace) => [Text('Error: $error')],
              loading: () => [],
            );
          },
        ),
      ),
    );
  }
}
