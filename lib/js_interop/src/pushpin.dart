@JS()
library pushpin;


import 'package:js/js.dart';

// Calls invoke JavaScript `JSON.stringify(obj)`.
@JS('pushpinInit')
external void pushpinInit(String id);
