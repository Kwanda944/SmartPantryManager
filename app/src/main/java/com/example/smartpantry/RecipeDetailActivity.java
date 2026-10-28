package com.example.smartpantry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        long recipeId = getIntent().getLongExtra("recipe_id", -1);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Recipe recipe = dbHelper.getRecipeById(recipeId);

        TextView recipeName = findViewById(R.id.tvRecipeName);
        TextView recipeIngredients = findViewById(R.id.tvRecipeIngredients);
        TextView recipeSteps = findViewById(R.id.tvRecipeSteps);

        if (recipe == null) {
            recipeName.setText("Recipe not found");
            recipeIngredients.setText("");
            recipeSteps.setText("");
            return;
        }

        recipeName.setText(recipe.getName());

        StringBuilder ingredientBuilder = new StringBuilder();
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            ingredientBuilder.append("• ")
                    .append(ingredient.getName())
                    .append(" - ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }
        recipeIngredients.setText(ingredientBuilder.toString().trim());
        recipeSteps.setText(recipe.getSteps());
    }
}
