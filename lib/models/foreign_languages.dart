import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'foreign_languages.freezed.dart';
part 'foreign_languages.g.dart';

ForeignLanguages foreignLanguagesFromJson(String str) =>
    ForeignLanguages.fromJson(json.decode(str));

String foreignLanguagesToJson(ForeignLanguages data) =>
    json.encode(data.toJson());

@freezed
class ForeignLanguages with _$ForeignLanguages {
  const factory ForeignLanguages({
    required List<Language> known,
  }) = _ForeignLanguages;

  factory ForeignLanguages.fromJson(Map<String, dynamic> json) =>
      _$ForeignLanguagesFromJson(json);
}

@freezed
class Language with _$Language {
  const factory Language({
    required String name,
    required String details,
  }) = _Language;

  factory Language.fromJson(Map<String, dynamic> json) =>
      _$LanguageFromJson(json);
}
