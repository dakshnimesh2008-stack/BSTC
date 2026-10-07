# BSTC — Offline Android App

## Included
- Native Android Java app (no third-party runtime libraries)
- Offline local SQLite database
- Supplied source PDFs bundled under `app/src/main/assets/papers/`
- Local PDF viewer using Android `PdfRenderer`
- Sections: Home, MCQs, Old Papers, Mock Tests, Results, Settings
- Local test history and settings

## Source policy
The supplied PDFs are the primary source. The bundled seed questions are intentionally conservative. Items without a supplied/visible official answer key are marked `verified=0` in the database; the app does not claim those answers are official.

## Official syllabus data used
- Rajasthan GK: 50 / 150
- Mental Ability: 50 / 150
- Teaching Aptitude: 50 / 150
- English: 20 / 60
- Hindi OR Sanskrit: 30 / 90
- Total: 200 / 600
- No negative marking

## Build
Open this folder in Android Studio with an installed Android SDK. The project uses Android Gradle Plugin 8.6.1 and compileSdk 35. Sync Gradle and build the debug APK.

This environment did not have a Gradle/Android build toolchain cached, so an APK was not fabricated. The complete source project and source PDFs are included.
