package com.example.a2_sohyeon_kim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DetailActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private TextView cookingTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        // Get recipe name from intent extra
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeContent = "";
        ImageView recipeImageView = findViewById(R.id.detailImageView);
        String recipeTimer = "";

        if (recipeName != null) {
            switch (recipeName) {
                case "TTEOKBOKKI":
                    recipeContent = "1: Soak rice cakes in cold water to soften if necessary.\n" +
                            "2: Mix water, gochujang, soy sauce, sugar, and garlic (if using) in a bowl.\n" +
                            "3: Heat vegetable oil in a pan over medium-high heat. Stir-fry cabbage (if using) for 1-2 minutes.\n" +
                            "4: Pour sauce into the pan. Bring to a boil. Add drained rice cakes. Stir gently to coat.\n" +
                            "5: Reduce heat to medium-low. Simmer for 7-10 minutes until rice cakes are tender and sauce thickens.\n" +
                            "6: Add green onions. Cook for 1-2 minutes until slightly wilted.\n" +
                            "7: Transfer to a plate or bowl. Optionally, top with boiled egg halves. Enjoy your Tteokbokki while hot!\n\n" +
                            "This recipe serves 2-3 as a snack or side dish. Adjust spice and sweetness levels to taste.";
                    recipeTimer = "30:00";
                    recipeImageView.setImageResource(R.drawable.tteokbokki);
                    break;
                case "GALBI":
                    recipeContent = "1: Mix soy sauce, brown sugar, mirin, honey, sesame oil, garlic, grated onion, and black pepper in a bowl.\n" +
                            "2: Marinate beef ribs in the mixture, refrigerated, for at least 2 hours or overnight.\n" +
                            "3: Preheat grill to medium-high heat.\n" +
                            "4: Grill ribs for 2-3 minutes per side until charred and cooked to desired doneness.\n" +
                            "5: Garnish with green onions and sesame seeds.\n" +
                            "6: Serve hot with steamed rice and Korean side dishes.\n\n" +
                            "Enjoy your delicious Galbi! Adjust sweetness and saltiness to taste by varying sugar and soy sauce amounts.";
                    recipeTimer = "50:00";
                    recipeImageView.setImageResource(R.drawable.galbi);
                    break;
                case "HOTTEOK":
                    recipeContent = "1: Mix lukewarm water, sugar, and yeast in a bowl. Let it sit until foamy (5-10 minutes).\n" +
                            "2: Combine flour and salt in a large bowl. Add the yeast mixture and knead into a smooth dough. Let it rise covered for 1-2 hours.\n" +
                            "3: Mix brown sugar, chopped nuts, and cinnamon. Optionally, add honey or maple syrup.\n" +
                            "4: Divide dough into 8 portions. Flatten each and place 1-2 tbsp of filling in the center.\n" +
                            "5: Seal edges and flatten into discs (3-4 inches in diameter).\n" +
                            "6: Fry in oil over medium heat until golden brown on both sides (2-3 minutes per side).\n" +
                            "7: Drain on paper towels and serve hot. Enjoy the crispy Hotteok with its sweet, nutty filling!\n\n" +
                            "This recipe makes delicious Hotteok, perfect for a sweet treat or snack. Adjust sweetness by varying sugar and filling ingredients to your liking.";;
                    recipeTimer = "45:00";
                    recipeImageView.setImageResource(R.drawable.hotteok);
                    break;
                default:
                    recipeContent = "Recipe details not found.";
            }
        }

        // set recipe elements
        TextView recipeNameTextView = findViewById(R.id.foodName);
        recipeNameTextView.setText(recipeName);

        TextView recipeContentTextView = findViewById(R.id.instructionTextView);
        recipeContentTextView.setText(recipeContent);

        recipeImageView = findViewById(R.id.detailImageView);

        cookingTimer = findViewById(R.id.cookingTimer);
        cookingTimer.setText(recipeTimer);
    }

    // Method to handle button click to launch TimerActivity
    public void launchTimerActivity(View view) {
        Intent intent = new Intent(this, TimerActivity.class);

        String message = cookingTimer.getText().toString();
        intent.putExtra("COOKING_TIMING", message);
        startActivity(intent);
    }
}