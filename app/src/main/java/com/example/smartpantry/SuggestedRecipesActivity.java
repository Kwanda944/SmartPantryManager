package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        dbHelper = new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView noMatches = findViewById(R.id.tvNoMatches);
        ArrayList<Recipe> suggestions = dbHelper.getSuggestedRecipes(dbHelper.getAllIngredients());

        if (suggestions.isEmpty()) {
            noMatches.setVisibility(View.VISIBLE);
        } else {
            noMatches.setVisibility(View.GONE);
        }

        RecipeAdapter adapter = new RecipeAdapter(suggestions, recipe -> {
            Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_suggested);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SuggestedRecipesActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_suggested) {
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(SuggestedRecipesActivity.this, SettingsActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
