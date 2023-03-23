import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'skills.freezed.dart';
part 'skills.g.dart';

Skills skillsFromJson(String str) => Skills.fromJson(json.decode(str));

String skillsToJson(Skills data) => json.encode(data.toJson());

@freezed
class Skills with _$Skills {
  const factory Skills({
    required List<Skill> skills,
  }) = _Skills;

  factory Skills.fromJson(Map<String, dynamic> json) => _$SkillsFromJson(json);
}

@freezed
class Skill with _$Skill {
  const factory Skill({
    required String name,
    @Default(false) bool primary,
    String? icon,
    required int level,
  }) = _Skill;

  factory Skill.fromJson(Map<String, dynamic> json) => _$SkillFromJson(json);
}
