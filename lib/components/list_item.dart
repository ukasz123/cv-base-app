import 'package:jaspr/jaspr.dart';

class ListItem extends StatelessComponent {
  final Component? icon;
  final String text;
  final Component? content;
  final List<String> _classes;

  ListItem({this.icon, required this.text, this.content, List<String>? classes})
      : _classes = classes ?? ['blue'];

  @override
  Iterable<Component> build(BuildContext context) sync* {
    yield DomComponent(
      tag: 'li',
      classes: ['collection-item', 'avatar', ..._classes],
      child: Builder(
        builder: (context) sync* {
          if (icon != null) yield icon!;
          yield Text(text);
          if (content != null) yield content!;
        },
      ),
    );
  }
}

class SimpleListItem extends StatelessComponent {
  final Component? icon;
  final String text;
  final Component? content;
  final List<String> _classes;

  SimpleListItem(
      {this.icon, required this.text, this.content, List<String>? classes})
      : _classes = classes ?? ['blue'];

  @override
  Iterable<Component> build(BuildContext context) sync* {
    var innerIcon = icon;
    if (icon != null) {
      innerIcon = DomComponent(
          tag: 'i',
          classes: [
            'material-icons',
            'circle',
            'darken-2',
            ..._classes,
          ],
          child: icon);
    }
    yield ListItem(
      text: text,
      icon: innerIcon,
      content: content,
      classes: _classes,
    );
  }
}
