import 'package:cv_app_base/models/foreign_languages.dart';
import 'package:cv_app_base/providers/data_provider.dart';

final languagesProvider = dataAccessProvider(
    DataAccessParams('foreign_languages', foreignLanguagesFromJson));
