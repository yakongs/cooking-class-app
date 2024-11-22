package com.example.a2_sohyeon_kim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                } else if (id == R.id.recipes) {
                    startActivity(new Intent(getApplicationContext(), RecipesActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                } else if (id == R.id.timer) {
                    startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                } else if (id == R.id.feedback) {
                    startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }

        });

    }
    // Method to handle button click to launch RecipesActivity
    public void launchRecipesActivity(View view) {
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }
}