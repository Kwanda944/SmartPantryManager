package com.example.smartpantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PANTRY = "pantry_items";
    public static final String TABLE_RECIPES = "recipes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PANTRY + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT, " +
                "expiry_date TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_RECIPES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "ingredients_text TEXT NOT NULL, " +
                "steps TEXT NOT NULL)");

        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        insertRecipe(db, "Tomato Pasta", "tomato|2|cup;garlic|2|cloves;pasta|200|g;olive oil|1|tbsp;parmesan|50|g",
                "1. Boil the pasta until tender.\n2. Sauté garlic in olive oil.\n3. Add chopped tomatoes and simmer.\n4. Stir pasta in and top with parmesan.");

        insertRecipe(db, "Veggie Omelette", "egg|3|whole;spinach|1|cup;tomato|1|whole;onion|0.5|cup;olive oil|1|tbsp",
                "1. Whisk the eggs.\n2. Sauté onion and spinach lightly.\n3. Add eggs and cook slowly.\n4. Fold in tomato and serve.");

        insertRecipe(db, "Chicken Rice Bowl", "chicken breast|250|g;rice|200|g;peas|1|cup;carrot|1|whole;soy sauce|1|tbsp",
                "1. Cook the rice.\n2. Fry chicken until cooked.\n3. Add carrot and peas.\n4. Mix in soy sauce and serve over rice.");

        insertRecipe(db, "Peanut Noodles", "noodles|200|g;peanut butter|2|tbsp;soy sauce|1|tbsp;carrot|1|whole;spring onion|2|stalks",
                "1. Cook noodles.\n2. Mix peanut butter and soy sauce into a sauce.\n3. Add carrot and spring onion.\n4. Toss noodles and serve.");

        insertRecipe(db, "Banana Oat Pancakes", "banana|2|whole;oats|100|g;egg|2|whole;milk|200|ml;honey|1|tbsp",
                "1. Mash bananas.\n2. Combine with oats, eggs, milk and honey.\n3. Cook on a pan until golden.\n4. Serve warm.");

        insertRecipe(db, "Bean Salad", "beans|250|g;tomato|2|whole;cucumber|1|whole;onion|0.5|cup;olive oil|1|tbsp",
                "1. Mix beans, tomatoes, cucumber and onion.\n2. Add olive oil.\n3. Season and rest briefly.\n4. Serve chilled.");

        insertRecipe(db, "Soup of the Day", "carrot|2|whole;onion|1|whole;potato|2|whole;garlic|2|cloves;stock|500|ml",
                "1. Sauté onion and garlic.\n2. Add carrot and potato.\n3. Pour in stock and simmer.\n4. Blend if desired and serve.");

        insertRecipe(db, "Baked Potatoes", "potato|3|whole;cheese|100|g;butter|1|tbsp;garlic|1|clove;milk|50|ml",
                "1. Bake potatoes until soft.\n2. Mix cheese, butter, garlic and milk.\n3. Scoop mixture into potatoes.\n4. Bake again to melt.");

        insertRecipe(db, "Fruit Smoothie", "banana|1|whole;strawberry|150|g;yogurt|200|ml;milk|100|ml;honey|1|tbsp",
                "1. Add all ingredients to blender.\n2. Blend until smooth.\n3. Pour into glass and serve immediately.");

        insertRecipe(db, "Tomato Soup", "tomato|4|whole;onion|1|whole;garlic|2|cloves;milk|200|ml;bread|2|slices",
                "1. Cook onion and garlic.\n2. Add chopped tomatoes and simmer.\n3. Blend until smooth.\n4. Stir in milk and serve with bread.");

        insertRecipe(db, "Coconut Curry", "coconut milk|400|ml;chickpeas|250|g;spinach|2|cups;onion|1|whole;garlic|2|cloves",
                "1. Fry onion and garlic.\n2. Add coconut milk and chickpeas.\n3. Simmer gently.\n4. Add spinach and serve.");

        insertRecipe(db, "Avocado Toast", "bread|2|slices;avocado|1|whole;tomato|1|whole;lemon|0.5|whole;salt|1|pinch",
                "1. Toast bread.\n2. Mash avocado with lemon and salt.\n3. Spread on toast.\n4. Top with tomato slices.");

        insertRecipe(db, "Greek Salad", "cucumber|1|whole;tomato|2|whole;olive oil|1|tbsp;feta|80|g;onion|0.5|cup",
                "1. Chop vegetables.\n2. Toss with olive oil.\n3. Top with feta and onion.\n4. Serve fresh.");

        insertRecipe(db, "Egg Fried Rice", "rice|200|g;egg|2|whole;peas|1|cup;carrot|1|whole;soy sauce|1|tbsp",
                "1. Cook rice and cool slightly.\n2. Scramble eggs.\n3. Add rice, peas and carrot.\n4. Stir in soy sauce and serve.");

        insertRecipe(db, "Cheesy Spinach Pasta", "pasta|200|g;spinach|2|cups;milk|200|ml;cheese|100|g;garlic|2|cloves",
                "1. Cook pasta.\n2. Warm milk and garlic.\n3. Add spinach and cheese.\n4. Stir through pasta and serve.");

        insertRecipe(db, "Chicken Veggie Stir Fry", "chicken breast|250|g;broccoli|2|cups;pepper|1|whole;soy sauce|1|tbsp;garlic|2|cloves",
                "1. Stir fry chicken and garlic.\n2. Add broccoli and pepper.\n3. Add soy sauce.\n4. Cook until crisp-tender.");

        insertRecipe(db, "Fruit Yogurt Bowl", "yogurt|200|ml;banana|1|whole;strawberry|100|g;granola|50|g;honey|1|tbsp",
                "1. Spoon yogurt into a bowl.\n2. Top with banana and strawberry.\n3. Add granola and honey.\n4. Serve immediately.");
    }

    private void insertRecipe(SQLiteDatabase db, String name, String ingredientsText, String steps) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("ingredients_text", ingredientsText);
        values.put("steps", steps);
        db.insert(TABLE_RECIPES, null, values);
    }

    public long addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        values.put("expiry_date", ingredient.getExpiryDate());
        return db.insert(TABLE_PANTRY, null, values);
    }

    public int updateIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        values.put("expiry_date", ingredient.getExpiryDate());
        return db.update(TABLE_PANTRY, values, "id = ?", new String[]{String.valueOf(ingredient.getId())});
    }

    public int deleteIngredient(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PANTRY, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PANTRY + " ORDER BY name ASC", null);
        if (cursor.moveToFirst()) {
            do {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                ingredient.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                ingredient.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow("quantity")));
                ingredient.setUnit(cursor.getString(cursor.getColumnIndexOrThrow("unit")));
                ingredient.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow("expiry_date")));
                list.add(ingredient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public Recipe getRecipeById(long recipeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES + " WHERE id = ?", new String[]{String.valueOf(recipeId)});
        Recipe recipe = null;
        if (cursor.moveToFirst()) {
            recipe = new Recipe();
            recipe.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
            recipe.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            recipe.setIngredients(parseIngredients(cursor.getString(cursor.getColumnIndexOrThrow("ingredients_text"))));
            recipe.setSteps(cursor.getString(cursor.getColumnIndexOrThrow("steps")));
        }
        cursor.close();
        return recipe;
    }

    public ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES + " ORDER BY name ASC", null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                recipe.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                recipe.setIngredients(parseIngredients(cursor.getString(cursor.getColumnIndexOrThrow("ingredients_text"))));
                recipe.setSteps(cursor.getString(cursor.getColumnIndexOrThrow("steps")));
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return recipes;
    }

    public ArrayList<Recipe> getSuggestedRecipes(List<Ingredient> pantryItems) {
        ArrayList<Recipe> suggestions = new ArrayList<>();
        for (Recipe recipe : getAllRecipes()) {
            if (canMakeRecipe(recipe, pantryItems)) {
                suggestions.add(recipe);
            }
        }
        return suggestions;
    }

    private ArrayList<RecipeIngredient> parseIngredients(String ingredientsText) {
        ArrayList<RecipeIngredient> ingredients = new ArrayList<>();
        if (ingredientsText == null || ingredientsText.trim().isEmpty()) {
            return ingredients;
        }

        String[] entries = ingredientsText.split(";");
        for (String entry : entries) {
            String trimmed = entry.trim();
            if (trimmed.isEmpty()) continue;
            String[] parts = trimmed.split("\\|");
            if (parts.length >= 3) {
                RecipeIngredient ingredient = new RecipeIngredient();
                ingredient.setName(parts[0].trim());
                ingredient.setQuantity(Double.parseDouble(parts[1].trim()));
                ingredient.setUnit(parts[2].trim());
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

    public static boolean canMakeRecipe(Recipe recipe, List<Ingredient> pantryItems) {
        Map<String, Double> pantryTotals = new HashMap<>();
        for (Ingredient item : pantryItems) {
            if (item == null || item.getName() == null) continue;
            String key = normalizeIngredientName(item.getName());
            pantryTotals.put(key, pantryTotals.getOrDefault(key, 0.0) + item.getQuantity());
        }

        for (RecipeIngredient required : recipe.getIngredients()) {
            String normalizedKey = normalizeIngredientName(required.getName());
            double pantryAmount = pantryTotals.getOrDefault(normalizedKey, 0.0);
            if (pantryAmount < required.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public static String normalizeIngredientName(String ingredientName) {
        if (ingredientName == null) return "";
        String value = ingredientName.toLowerCase(Locale.getDefault()).trim();
        value = value.replaceAll("[^a-z0-9\\s]", "");
        value = value.replaceAll("\\s+", " ");

        if (value.endsWith("ies") && value.length() > 4 && !value.endsWith("sses")) {
            value = value.substring(0, value.length() - 3) + "y";
        } else if (value.endsWith("es") && value.length() > 3 && !value.endsWith("ses") && !value.endsWith("xes") && !value.endsWith("zes") && !value.endsWith("ches") && !value.endsWith("shes")) {
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("s") && value.length() > 2 && !value.endsWith("ss")) {
            value = value.substring(0, value.length() - 1);
        }

        value = value.replace("garlic cloves", "garlic");
        value = value.replace("garlic clove", "garlic");
        value = value.replace("spring onions", "spring onion");
        value = value.replace("tomatoes", "tomato");
        value = value.replace("peas", "pea");
        value = value.replace("onions", "onion");
        value = value.replace("carrots", "carrot");
        value = value.replace("eggs", "egg");
        value = value.replace("potatoes", "potato");
        value = value.replace("strawberries", "strawberry");
        value = value.replace("bananas", "banana");

        return value.trim();
    }
}
