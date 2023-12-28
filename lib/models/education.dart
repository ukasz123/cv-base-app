import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'education.freezed.dart';
part 'education.g.dart';

Education educationFromJson(String str) => Education.fromJson(json.decode(str));

String educationToJson(Education data) => json.encode(data.toJson());

@freezed
class Education with _$Education {
  const factory Education({
    // ignore: invalid_annotation_target
    @JsonKey(name: 'college_studies')
    required List<CollegeStudy> collegeStudies,
  }) = _Education;

  factory Education.fromJson(Map<String, dynamic> json) =>
      _$EducationFromJson(json);
}

@freezed
class CollegeStudy with _$CollegeStudy {
  const factory CollegeStudy({
    required String title,
    required String period,
  }) = _CollegeStudy;

  factory CollegeStudy.fromJson(Map<String, dynamic> json) =>
      _$CollegeStudyFromJson(json);
}
