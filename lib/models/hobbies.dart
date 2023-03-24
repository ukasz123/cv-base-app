import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'hobbies.freezed.dart';
part 'hobbies.g.dart';

Hobbies hobbiesFromJson(String str) => Hobbies.fromJson(json.decode(str));

String hobbiesToJson(Hobbies data) => json.encode(data.toJson());

@freezed
class Hobbies with _$Hobbies {
  const factory Hobbies({
    required List<Hobby> hobbies,
  }) = _Hobbies;

  factory Hobbies.fromJson(Map<String, dynamic> json) =>
      _$HobbiesFromJson(json);
}

@freezed
class Hobby with _$Hobby {
  const factory Hobby({
    required String name,
    required String iconCode,
  }) = _Hobby;

  factory Hobby.fromJson(Map<String, dynamic> json) => _$HobbyFromJson(json);
}
