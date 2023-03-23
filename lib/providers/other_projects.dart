import 'package:cv_app_base/models/other_projects.dart';
import 'package:cv_app_base/providers/data_provider.dart';

final otherProjectsProvider = dataAccessProvider(
    DataAccessParams<OtherProjects>('other_projects', otherProjectsFromJson));
