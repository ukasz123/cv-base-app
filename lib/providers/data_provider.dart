import 'dart:async';

import 'package:cv_app_base/translation.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';
import 'dart:convert';

import 'package:http/http.dart';

class DataAccessParams<T> {
  final String path;
  final FutureOr<T> Function(String) convert;

  DataAccessParams(this.path, this.convert);
}

FutureProvider<T> dataAccessProvider<T>(
        DataAccessParams<T> params) =>
    FutureProvider<T>((ref) async {
      final selectedLang = ref.watch(selectedLanguageProvider).name;
      final filePath = "public/data/${params.path}/$selectedLang.json";
      final contentResponse = await get(Uri(path: filePath));
      if (contentResponse.statusCode == 200) {
        final stringContent = Utf8Decoder().convert(contentResponse.bodyBytes);
        final content = params.convert(stringContent);
        return (await content);
      }
      throw Exception("Can't load ${params.path}.");
    });
