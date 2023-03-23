import 'package:freezed_annotation/freezed_annotation.dart';
import 'dart:convert';

part 'contact_data.freezed.dart';
part 'contact_data.g.dart';

ContactData contactDataFromJson(String str) =>
    ContactData.fromJson(json.decode(str));

String contactDataToJson(ContactData data) => json.encode(data.toJson());

@freezed
class ContactData with _$ContactData {
  const factory ContactData({
    required String name,
    required String address,
    required String linkedin,
    required String github,
    required String bitbucket,
  }) = _ContactData;

  factory ContactData.fromJson(Map<String, dynamic> json) =>
      _$ContactDataFromJson(json);
}
