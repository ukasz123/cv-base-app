import 'package:cv_app_base/components/copyrights.dart';
import 'package:cv_app_base/navigation.dart';
import 'package:cv_app_base/sections/contact_data.dart';
import 'package:cv_app_base/sections/education.dart';
import 'package:cv_app_base/sections/hobbies.dart';
import 'package:cv_app_base/sections/languages.dart';
import 'package:cv_app_base/sections/other_projects.dart';
import 'package:cv_app_base/sections/skills.dart';
import 'package:cv_app_base/sections/work_timeline.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class App extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield _NavigationBar();
    yield DomComponent(
      tag: 'main',
      children: [
        ContactData(),
        Skills(),
        WorkTimeline(),
        OtherProjects(),
        Education(),
        ForeignLanguages(),
        Hobbies(),
        Copyrights(),
      ],
    );
  }
}

class _NavigationBar extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield NavigationPanel(
      anchors: [
        'contactData',
        'skills',
        'workTimeline',
        'otherProjects',
        'education',
        'foreignLanguages',
        'hobbies',
      ],
      languageOptions: [
        MapEntry('pl', () {
          context.read(selectedLanguageProvider.notifier).state =
              SupportedLanguages.pl;
        }),
        MapEntry('en', () {
          context.read(selectedLanguageProvider.notifier).state =
              SupportedLanguages.en;
        }),
      ],
      selectedLang: context.watch(selectedLanguageProvider),
    );
  }
}
