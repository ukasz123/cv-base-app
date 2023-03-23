#!/bin/bash
cd cvPdfGenerator
chmod u+x ./gradlew
./gradlew :generatePDF -Pbase=../web/
