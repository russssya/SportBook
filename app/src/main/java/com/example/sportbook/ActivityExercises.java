package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityExercises extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Init();
    }

    @SuppressLint("NonConstantResourceId")
    private void Init(){
        bottomNavigationView=findViewById(R.id.navigation_bottom);
        bottomNavigationView.setSelectedItemId(R.id.menu_exercises);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_week:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                case R.id.menu_calendar:
                    startActivity(new Intent(getApplicationContext(), ActivityCalendar.class));
                    finish();
                    return true;
                case R.id.menu_exercises:
                    return true;
                case R.id.menu_profile:
                    startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
                    finish();
                    return true;
            }
            return false;
        });
    }
}