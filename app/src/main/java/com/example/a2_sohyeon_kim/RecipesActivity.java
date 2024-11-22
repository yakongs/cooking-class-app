package com.example.a2_sohyeon_kim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class RecipesActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.recipes) {
                    startActivity(new Intent(getApplicationContext(), RecipesActivity.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.timer) {
                    startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.feedback) {
                    startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        // Set click listeners for each recipe TextView
        TextView tteokbokkiTextView = findViewById(R.id.textView);
        TextView galbiTextView = findViewById(R.id.textView2);
        TextView hotteokTextView = findViewById(R.id.textView3);

        tteokbokkiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsActivity(getString(R.string.tteokbokki));
            }
        });

        galbiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsActivity(getString(R.string.galbi));
            }
        });

        hotteokTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsActivity(getString(R.string.hotteok));
            }
        });
    }

    // Method to open DetailsActivity with recipeName argument
    private void openDetailsActivity(String string) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("recipeName", string);
        startActivity(intent);
    }
}