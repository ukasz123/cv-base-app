import 'package:cv_app_base/models/education.dart';
import 'package:cv_app_base/providers/data_provider.dart';

final educationProvider =
    dataAccessProvider(DataAccessParams('education', educationFromJson));
