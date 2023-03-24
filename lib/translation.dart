import 'dart:convert';

import 'package:http/http.dart';
import 'package:jaspr/jaspr.dart';
import 'package:jaspr_riverpod/jaspr_riverpod.dart';

enum SupportedLanguages {
  pl,
  en,
}


final selectedLanguageProvider =
    StateProvider<SupportedLanguages>((_) => SupportedLanguages.pl);

final _currentTranslationProvider =
    FutureProvider<Map<String, String>>((ref) async {
  final translationFilePath =
      "public/data/translations.${ref.watch(selectedLanguageProvider).name}.json";
  final translationContentResponse = await get(Uri(path: translationFilePath));
  if (translationContentResponse.statusCode == 200) {
    final stringContent = Utf8Decoder().convert(translationContentResponse.bodyBytes);
    final content = jsonDecode(stringContent);
    return (content as Map).cast<String, String>();
  }
  throw Exception("Can't load translations.");
});

final i18nProvider = Provider.family<AsyncValue<String>, String>((ref, code) {
  final translationsLoader = ref.watch(_currentTranslationProvider);
  return translationsLoader.whenData((translations) => translations[code]!);
});

extension I18nContextExtension on BuildContext {
  String i18n(String code) {
    final val = watch(i18nProvider(code));
    return val.maybeWhen(
      orElse: () => '',
      data: (text) => text,
    );
  }
}
