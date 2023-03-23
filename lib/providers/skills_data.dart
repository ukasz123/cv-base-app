import 'dart:convert';

import 'package:cv_app_base/models/skills.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

import 'package:http/http.dart';

final _skillsDataProvider = FutureProvider<Skills>((ref) async {
  final filePath = "public/data/skills/skills_data.json";
  final contentResponse = await get(Uri(path: filePath));
  if (contentResponse.statusCode == 200) {
    final stringContent = Utf8Decoder().convert(contentResponse.bodyBytes);
    final content = skillsFromJson(stringContent);
    return content;
  }
  throw Exception("Can't load skills.");
});

final skillsProvider = Provider<AsyncValue<List<List<Skill>>>>((ref) {
  return ref.watch(_skillsDataProvider).whenData((skillsData) {
    final skills = skillsData.skills.toList()
      ..sort(
        ((a, b) => -a.level.compareTo(b.level)),
      );

    final primarySkills = skills.where((skill) => skill.primary);
    final secondarySkills = skills.where((skill) => !skill.primary);

    return [
      ...primarySkills.windowed(3),
      ...secondarySkills.windowed(4),
    ];
  });
});

extension _Windowed<T> on Iterable<T> {
  Iterable<List<T>> windowed(int size) sync* {
    var movingWindow = <T>[];
    for (var el in this) {
      movingWindow.add(el);
      if (movingWindow.length == size) {
        yield movingWindow;
        movingWindow = <T>[];
      }
    }
    yield movingWindow;
  }
}
