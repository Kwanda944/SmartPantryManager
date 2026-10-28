package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class AddEditIngredientActivity extends AppCompatActivity {
    private EditText editName, editQuantity, editExpiryDate;
    private Spinner spinnerUnit;
    private DatabaseHelper dbHelper;
    private long ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        dbHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.editIngredientName);
        editQuantity = findViewById(R.id.editIngredientQuantity);
        spinnerUnit = findViewById(R.id.spinnerIngredientUnit);
        editExpiryDate = findViewById(R.id.editExpiryDate);
        Button btnSave = findViewById(R.id.btnSaveIngredient);
        TextView tvTitle = findViewById(R.id.tvTitle);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Arrays.asList("unit", "g", "kg", "ml", "l", "cup", "cups", "whole", "cloves", "pieces", "tbsp", "tsp", "slice", "slices"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);

        ingredientId = getIntent().getLongExtra("ingredient_id", -1);
        if (ingredientId != -1) {
            tvTitle.setText("Edit Ingredient");
            Ingredient ingredient = getIngredientById(ingredientId);
            if (ingredient != null) {
                editName.setText(ingredient.getName());
                editQuantity.setText(String.valueOf(ingredient.getQuantity()));
                int unitIndex = adapter.getPosition(ingredient.getUnit());
                if (unitIndex >= 0) spinnerUnit.setSelection(unitIndex);
                editExpiryDate.setText(ingredient.getExpiryDate() == null ? "" : ingredient.getExpiryDate());
            }
        } else {
            tvTitle.setText("Add Ingredient");
        }

        btnSave.setOnClickListener(v -> saveIngredient());
    }

    private Ingredient getIngredientById(long id) {
        for (Ingredient ingredient : dbHelper.getAllIngredients()) {
            if (ingredient.getId() == id) return ingredient;
        }
        return null;
    }

    private void saveIngredient() {
        String name = editName.getText().toString().trim();
        String quantityText = editQuantity.getText().toString().trim();
        String expiryDate = editExpiryDate.getText().toString().trim();
        String unit = spinnerUnit.getSelectedItem().toString();

        if (TextUtils.isEmpty(name)) {
            editName.setError("Ingredient name is required");
            return;
        }

        if (TextUtils.isEmpty(quantityText)) {
            editQuantity.setError("Quantity is required");
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {
            editQuantity.setError("Please enter a valid number");
            return;
        }

        if (quantity <= 0) {
            editQuantity.setError("Quantity must be greater than zero");
            return;
        }

        if (!"unit".equals(unit) && !TextUtils.isEmpty(expiryDate)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            format.setLenient(false);
            try {
                Date date = format.parse(expiryDate);
                if (date == null) {
                    throw new ParseException("Invalid date", 0);
                }
            } catch (ParseException e) {
                editExpiryDate.setError("Enter date as yyyy-MM-dd");
                return;
            }
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
        ingredient.setUnit(unit.equals("unit") ? "" : unit);
        ingredient.setExpiryDate(expiryDate);

        if (ingredientId != -1) {
            ingredient.setId(ingredientId);
            int rows = dbHelper.updateIngredient(ingredient);
            if (rows > 0) {
                Toast.makeText(this, "Ingredient updated", Toast.LENGTH_SHORT).show();
            }
        } else {
            long newId = dbHelper.addIngredient(ingredient);
            if (newId > 0) {
                Toast.makeText(this, "Ingredient added", Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}
