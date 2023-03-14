import 'package:cv_app_base/models/contact_data.dart';
import 'package:cv_app_base/providers/data_provider.dart';

final contactDataProvider = dataAccessProvider(
    DataAccessParams<ContactData>('contactdata', contactDataFromJson));
