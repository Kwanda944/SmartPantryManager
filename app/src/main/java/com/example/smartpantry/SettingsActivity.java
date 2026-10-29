package com.example.smartpantry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "smart_pantry_settings";
    private static final String KEY_EXPIRY_ALERTS = "expiry_alerts";
    private static final String KEY_METRIC_UNITS = "metric_units";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SwitchCompat switchExpiryAlerts = findViewById(R.id.switchExpiryAlerts);
        SwitchCompat switchMetricUnits = findViewById(R.id.switchMetricUnits);

        switchExpiryAlerts.setChecked(prefs.getBoolean(KEY_EXPIRY_ALERTS, true));
        switchMetricUnits.setChecked(prefs.getBoolean(KEY_METRIC_UNITS, true));

        switchExpiryAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_EXPIRY_ALERTS, isChecked).apply();
        });

        switchMetricUnits.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_METRIC_UNITS, isChecked).apply();
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_suggested) {
                startActivity(new Intent(SettingsActivity.this, SuggestedRecipesActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_settings) {
                return true;
            }
            return false;
        });
    }
}
