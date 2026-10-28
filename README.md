# Smart Pantry Manager

Smart Pantry Manager is a Java Android application that helps users track pantry ingredients and suggest only recipes that can be cooked using what they already have at home. The app follows a strict matching rule: a recipe is shown only when every ingredient is present in the pantry in at least the required quantity.

## Database choice

This app uses SQLite via `SQLiteOpenHelper` because it is lightweight, fully local, persistent across app sessions, and matches the Android module content on device storage and CRUD handling.

## Features

- Add, edit, and delete pantry ingredients
- View all pantry items in a RecyclerView list
- Seeded recipe database with 16 recipes
- Strict recipe matching logic based on pantry contents
- Recipe detail screen with ingredients and steps
- Settings screen with expiry alerts and unit preferences
- Empty-state feedback when no recipe matches

## Setup and run

1. Open the project in Android Studio.
2. Let Gradle sync complete.
3. Connect an Android emulator or a physical device.
4. Run the app from the `app` module.

## Project structure

- `app/src/main/java` — Java activities, adapters, database helper, and models
- `app/src/main/res/layout` — XML screen layouts
- `app/src/main/res/values` — app colors, themes, and strings
- `app/src/main/AndroidManifest.xml` — activity registrations and app metadata

## Notes

- The app intentionally excludes Google Maps and GPS-based features.
- All pantry data is saved locally in SQLite so it persists between app sessions.
