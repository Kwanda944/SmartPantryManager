package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private PantryAdapter adapter;
    private List<Ingredient> pantryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        pantryList = dbHelper.getAllIngredients();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPantry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PantryAdapter(pantryList, new PantryAdapter.OnIngredientActionListener() {
            @Override
            public void onEdit(Ingredient ingredient) {
                Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
                intent.putExtra("ingredient_id", ingredient.getId());
                startActivity(intent);
            }

            @Override
            public void onDelete(Ingredient ingredient) {
                dbHelper.deleteIngredient(ingredient.getId());
                refreshPantry();
            }
        });
        recyclerView.setAdapter(adapter);

        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        TextView emptyView = findViewById(R.id.tvEmptyPantry);
        if (pantryList.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_pantry);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_pantry) {
                return true;
            } else if (id == R.id.nav_suggested) {
                startActivity(new Intent(MainActivity.this, SuggestedRecipesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPantry();
    }

    private void refreshPantry() {
        pantryList.clear();
        pantryList.addAll(dbHelper.getAllIngredients());
        adapter.notifyDataSetChanged();
        TextView emptyView = findViewById(R.id.tvEmptyPantry);
        emptyView.setVisibility(pantryList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
