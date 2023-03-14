import 'dart:convert';

import 'package:cv_app_base/models/projects.dart';
import 'package:cv_app_base/translation.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

import 'package:http/http.dart';

final _projectsProvider = FutureProvider<Projects>((ref) async {
  final filePath = "public/data/history/projects.json";
  final contentResponse = await get(Uri(path: filePath));
  if (contentResponse.statusCode == 200) {
    final stringContent = Utf8Decoder().convert(contentResponse.bodyBytes);
    final content = projectsFromJson(stringContent);
    return content;
  }
  throw Exception("Can't load skills.");
});

final projectsProvider = Provider<AsyncValue<List<Project>>>(
  (ref) => ref
      .watch(_projectsProvider)
      .whenData((value) => value.projects)
      .whenData((list) => list.toList()
        ..sort(
          (a, b) => -(a.to ?? '9999-12').compareTo(b.to ?? '9999-12'),
        )),
);

final _projectDescriptionProvider =
    FutureProvider.family<ProjectDescription, Project>(
  (ref, project) async {
    final projectCode = project.code;
    final selectedLang = ref.watch(selectedLanguageProvider).name;
    final filePath = 'public/data/history/$projectCode/$selectedLang.json';
    try {
      final contentResponse = await get(Uri(path: filePath));
      if (contentResponse.statusCode == 200) {
        final stringContent = Utf8Decoder().convert(contentResponse.bodyBytes);
        final json = jsonDecode(stringContent);
        final content = ProjectDescription.fromJson(json);
        return content;
      }
    } finally {}

    return ProjectDescription(title: project.name);
  },
);

final projectDescriptionProvider = Provider.family<ProjectDescription, Project>(
  (ref, project) => ref.watch(_projectDescriptionProvider(project)).maybeWhen(
        orElse: () => ProjectDescription(title: project.name),
        data: (data) => data,
      ),
);
