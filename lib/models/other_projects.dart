import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'other_projects.freezed.dart';
part 'other_projects.g.dart';

OtherProjects otherProjectsFromJson(String str) =>
    OtherProjects.fromJson(json.decode(str));

String otherProjectsToJson(OtherProjects data) => json.encode(data.toJson());

@freezed
class OtherProjects with _$OtherProjects {
  const factory OtherProjects({
    required List<Project> projects,
  }) = _OtherProjects;

  factory OtherProjects.fromJson(Map<String, dynamic> json) =>
      _$OtherProjectsFromJson(json);
}

@freezed
class Project with _$Project {
  const factory Project({
    required String title,
    required String description,
    String? repositoryUrl,
    required List<String> skills,
    @Default([]) List<DeploymentUrl> deploymentUrls,
    String? imageUrl,
  }) = _Project;

  factory Project.fromJson(Map<String, dynamic> json) =>
      _$ProjectFromJson(json);
}

@freezed
class DeploymentUrl with _$DeploymentUrl {
  const factory DeploymentUrl({
    required String type,
    required String url,
  }) = _DeploymentUrl;

  factory DeploymentUrl.fromJson(Map<String, dynamic> json) =>
      _$DeploymentUrlFromJson(json);
}
