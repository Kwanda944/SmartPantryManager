package com.example.smartpantry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {
    private final List<Ingredient> ingredients;
    private final OnIngredientActionListener listener;

    public interface OnIngredientActionListener {
        void onEdit(Ingredient ingredient);
        void onDelete(Ingredient ingredient);
    }

    public PantryAdapter(List<Ingredient> ingredients, OnIngredientActionListener listener) {
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.tvIngredientName.setText(ingredient.getName());
        holder.tvQuantityValue.setText(String.format("%.1f %s", ingredient.getQuantity(), ingredient.getUnit()));
        holder.tvExpiryValue.setText(ingredient.getExpiryDate() == null || ingredient.getExpiryDate().isEmpty() ? "No expiry date" : ingredient.getExpiryDate());

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(ingredient));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(ingredient));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {
        TextView tvIngredientName;
        TextView tvQuantityValue;
        TextView tvExpiryValue;
        Button btnEdit;
        Button btnDelete;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredientName = itemView.findViewById(R.id.tvIngredientName);
            tvQuantityValue = itemView.findViewById(R.id.tvQuantityValue);
            tvExpiryValue = itemView.findViewById(R.id.tvExpiryValue);
            btnEdit = itemView.findViewById(R.id.btnEditItem);
            btnDelete = itemView.findViewById(R.id.btnDeleteItem);
        }
    }
}
