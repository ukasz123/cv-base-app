import 'dart:async';

import 'package:cv_app_base/components/copyrights.dart';
import 'package:cv_app_base/js_interop/pushpin.dart';
import 'package:cv_app_base/navigation.dart';
import 'package:cv_app_base/sections/contact_data.dart';
import 'package:cv_app_base/sections/skills.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr/html.dart';
import 'package:jaspr/jaspr.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

class App extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    // left pane
    yield PushPin(
      classes: ['hide-on-small-only', 'tableOfContents'],
      id: 'tableOfContents',
      child: _LeftPane(),
    );
    yield _MobileTopBar();
    yield ContactData();
    yield SkillsSection();
    yield Copyrights();
  }
}

class _LeftPane extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
      tag: 'div',
      classes: ['col m3 l2'],
      child: NavigationPanel(
        anchors: [
          'contactData',
          'skills',
          'workTimeline',
          'projects',
          'otherProjects',
          'education',
          'foreignLanguages',
          'hobbies',
        ],
        languageOptions: [
          MapEntry('pl', () {}),
          MapEntry('en', () {}),
        ],
        selectedLang: context.watch(selectedLanguageProvider),
      ),
    );
  }
}

class _MobileTopBar extends StatelessComponent {
  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield Builder(builder: (context) sync* {
      yield DomComponent(
        tag: 'nav',
        classes: ['hide-on-med-and-up', 'teal'],
        id: 'languageSelector',
        child: DomComponent(tag: 'div', classes: [
          'nav-wrapper',
          'container'
        ], children: [
          DomComponent(
            tag: 'div',
            classes: ['left'],
            child: Builder(
              builder: (innerContext) sync* {
                final txt = innerContext.i18n('cvTitle');
                yield Text(txt);
              },
            ),
          ),
          DomComponent(
            tag: 'div',
            classes: ['right'],
            child: DomComponent(
              tag: 'div',
              classes: ['btn-flat', 'white-text'],
              child: Text(context.i18n('lang')),
            ),
          ),
          DomComponent(
            tag: 'div',
            classes: ['right'],
            child: DomComponent(
              tag: 'div',
              classes: ['btn-flat'],
              child: Text('TODO: pdfLink'),
            ),
          ),
        ]),
      );
    });
  }
}
