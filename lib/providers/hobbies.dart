import 'package:cv_app_base/models/hobbies.dart';
import 'package:cv_app_base/providers/data_provider.dart';

final hobbiesProvider = dataAccessProvider(
    DataAccessParams('hobbies', (input) => hobbiesFromJson(input).hobbies));
