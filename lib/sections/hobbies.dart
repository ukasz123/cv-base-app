import 'package:cv_app_base/components/content_section.dart';
import 'package:cv_app_base/components/content_title.dart';
import 'package:cv_app_base/providers/hobbies.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class Hobbies extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield ContentSection(
        id: 'hobbies',
        classes: ['bg-gravel'],
        child: Builder(builder: (context) sync* {
          yield ContentTitle(context.i18n('hobbies'));
          yield ul(
            context.watch(hobbiesProvider).maybeWhen(
                  orElse: () => [],
                  data: (hobbies) => hobbies
                      .map(
                        (hobby) => li(
                          [
                            img(src: 'public/images/ic_${hobby.iconCode}.svg'),
                            text(hobby.name),
                          ],
                          classes: ['chip'],
                        ),
                      )
                      .toList(growable: false),
                ),
          );
        }));
  }
}
