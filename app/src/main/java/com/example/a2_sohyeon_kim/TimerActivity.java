package com.example.a2_sohyeon_kim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TimerActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private TextView recipeInfoTextView, timerTextView;
    private SeekBar timerSeekBar;
    private ProgressBar progressBar;
    private Button startButton, pauseButton, resetButton;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long totalTimeInMillis = 3600000; // Example total cooking time (10 minutes)
    private long timeLeftInMillis = totalTimeInMillis;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.timer);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0,0);
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

        recipeInfoTextView = findViewById(R.id.recipeInfoTextView);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        progressBar = findViewById(R.id.progressBar);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        // Set up recipe info
        recipeInfoTextView.setText("Set Cooking Time: " + millisToTimer(totalTimeInMillis));


        // Set up SeekBar
        timerSeekBar.setMax(60); // Maximum value is 60 minutes
        timerSeekBar.setProgress(60); // Initial progress is 60 minutes (1 hour)
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update total time based on SeekBar progress
                totalTimeInMillis = progress * 60000L; // Convert minutes to milliseconds

                // If the timer is running, reset it with the new total time
                if (isTimerRunning) {
                    resetTimer();
                } else {
                    // Update recipe info if the timer is not running
                    recipeInfoTextView.setText("Set Cooking Time: " + millisToTimer(totalTimeInMillis));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set up start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        // Set up pause button
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        // Set up reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });


        // Receive intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("COOKING_TIMING")) {
            String cookingTiming = intent.getStringExtra("COOKING_TIMING");

            // Convert cookingTiming string to milliseconds (if needed)
            long totalTimeInMillis = convertTimeToMillis(cookingTiming); // Implement this method

            // Set up SeekBar and UI components based on totalTimeInMillis
            timerSeekBar.setProgress((int) (totalTimeInMillis / 60000)); // Convert milliseconds to minutes for SeekBar
            recipeInfoTextView.setText("Set Cooking Time: " + cookingTiming); // Display cooking timing info
        }
    }

    private void startTimer() {
        if (!isTimerRunning) {
            // Calculate total time from SeekBar progress
            totalTimeInMillis = timerSeekBar.getProgress() * 60000L; // Convert minutes to milliseconds

            // Initialize countdown timer with updated total time
            countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateTimer();
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    // Timer finished, handle accordingly
                    timerTextView.setText("00:00");
                    progressBar.setProgress(0);
                    isTimerRunning = false;
                }
            }.start();

            // Update UI
            isTimerRunning = true;
        }
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }

    private void resetTimer() {
        if (!isTimerRunning) {
            timeLeftInMillis = totalTimeInMillis;
            updateTimer();
        }
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        @SuppressLint("DefaultLocale") String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);

        // Calculate progress as a percentage of the total time
        int progress = (int) (((float) timeLeftInMillis / totalTimeInMillis) * 100);
        progressBar.setProgress(progress);
    }

    @SuppressLint("DefaultLocale")
    private String millisToTimer(long millis) {
        int minutes = (int) (millis / 1000) / 60;
        int seconds = (int) (millis / 1000) % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    private long convertTimeToMillis(String timeString) {
        // Split the timeString into hours, minutes, and seconds
        String[] parts = timeString.split(":");
        int hours = 0, minutes = 0, seconds = 0;

        if (parts.length == 2) {
            minutes = Integer.parseInt(parts[0]);
            seconds = Integer.parseInt(parts[1]);
        } else if (parts.length == 3) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
        }

        // Convert hours, minutes, and seconds to milliseconds
        return hours * 3600000L + minutes * 60000L + seconds * 1000L;
    }
}