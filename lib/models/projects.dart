import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'projects.freezed.dart';
part 'projects.g.dart';

Projects projectsFromJson(String str) => Projects.fromJson(json.decode(str));

String projectsToJson(Projects data) => json.encode(data.toJson());

@freezed
class Projects with _$Projects {
  const factory Projects({
    required List<Project> projects,
  }) = _Projects;

  factory Projects.fromJson(Map<String, dynamic> json) =>
      _$ProjectsFromJson(json);
}

@freezed
class Project with _$Project {
  const factory Project({
    required String code,
    required String name,
    required String from,
    @Default([]) List<String> skillsUsed,
    String? company,
    String? to,
  }) = _Project;

  factory Project.fromJson(Map<String, dynamic> json) =>
      _$ProjectFromJson(json);
}

@freezed
class ProjectDescription with _$ProjectDescription {
  const factory ProjectDescription({
    String? title,
    String? description,
    List<ProjectUrl>? urls,
  }) = _ProjectDescription;

  factory ProjectDescription.fromJson(Map<String, dynamic> json) =>
      _$ProjectDescriptionFromJson(json);
}

@freezed
class ProjectUrl with _$ProjectUrl {
  factory ProjectUrl({
    required String title,
    required String url,
  }) = _ProjectUrl;

  factory ProjectUrl.fromJson(Map<String, dynamic> json) =>
      _$ProjectUrlFromJson(json);
}
