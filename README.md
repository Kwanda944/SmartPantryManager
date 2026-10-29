# Smart Pantry Manager

Smart Pantry Manager is a Java Android application that helps users track the ingredients they already have at home and suggests only recipes they can make right now. The app follows the strict business rule required in the assignment: a recipe is only shown when each required ingredient is present in the pantry in at least the required quantity.

## Database choice

This project uses SQLite with `SQLiteOpenHelper` because it is local, persistent, lightweight, and directly fits the Android persistence topic covered in the module.

## Features

- Add, edit, and delete pantry ingredients
- View pantry items in a RecyclerView
- Seeded recipe database with 16 recipes
- Strict recipe matching logic using pantry quantities
- Recipe detail screen with ingredients and preparation steps
- Settings screen for expiry alerts and unit preferences
- Empty-state feedback when no recipes match

## Assignment requirements covered

- Java Android app built in Android Studio
- Minimum four screen flow with navigation
- RecyclerView + custom adapter
- Intents used to navigate between screens
- CRUD operations with persistent SQLite storage
- Strict recipe matching logic
- No GPS or maps dependency

## Setup and run

1. Open the project in Android Studio.
2. Let Gradle sync complete.
3. Connect an Android emulator or physical device.
4. Select the `app` module and run the application.

## Project structure

- `app/src/main/java` — Java activities, adapters, database helper, and models
- `app/src/main/res/layout` — screen layouts
- `app/src/main/res/menu` — bottom navigation menu
- `app/src/main/res/values` — strings, colors, and themes
- `app/src/main/AndroidManifest.xml` — activity registrations

## Notes

- The app stores data locally using SQLite so it still exists after the app is closed and reopened.
- Recipes are filtered using a strict matching rule and do not appear unless the pantry contains everything required.
