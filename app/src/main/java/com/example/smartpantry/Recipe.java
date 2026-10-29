package com.example.smartpantry;

import java.util.ArrayList;

public class Recipe {
    private long id;
    private String name;
    private ArrayList<RecipeIngredient> ingredients;
    private String steps;

    public Recipe() {
        ingredients = new ArrayList<>();
    }

    public Recipe(long id, String name, ArrayList<RecipeIngredient> ingredients, String steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ArrayList<RecipeIngredient> getIngredients() { return ingredients; }
    public void setIngredients(ArrayList<RecipeIngredient> ingredients) { this.ingredients = ingredients; }

    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
}
