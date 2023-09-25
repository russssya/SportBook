package com.example.sportbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sportbook.Adapters.ListAdapterWeekWorkouts;
import com.example.sportbook.Database.RoomDB;
import com.example.sportbook.Models.Workout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapterWeekWorkouts.ItemClickListener {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab_create_train;
    RecyclerView recyclerView_weekWorkouts;
    List<Workout> list_workouts = new ArrayList<>();
    ListAdapterWeekWorkouts listAdapterWeekWorkouts;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    @SuppressLint("NonConstantResourceId")
    private void Init(){
        bottomNavigationView=findViewById(R.id.navigation_bottom);
        fab_create_train=findViewById(R.id.fab_create_train);
        database=RoomDB.getInstance(this);
        recyclerView_weekWorkouts=findViewById(R.id.recycler_week_workouts);
        list_workouts = database.mainDAO().getAll();

        updateRecycler();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_week:
                    return true;
                case R.id.menu_calendar:
                    startActivity(new Intent(getApplicationContext(), ActivityCalendar.class));
                    finish();
                    return true;
                case R.id.menu_exercises:
                    startActivity(new Intent(getApplicationContext(), ActivityExercises.class));
                    finish();
                    return true;
                case R.id.menu_profile:
                    startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
                    finish();
                    return true;
            }
            return false;
        });

        fab_create_train.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityTrain.class);
            startActivityForResult(intent, 101);
        });
    }

    private void updateRecycler(){
        recyclerView_weekWorkouts.setHasFixedSize(true);
        recyclerView_weekWorkouts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listAdapterWeekWorkouts=new ListAdapterWeekWorkouts(getApplicationContext(), list_workouts);
        recyclerView_weekWorkouts.setAdapter(listAdapterWeekWorkouts);
        listAdapterWeekWorkouts.setClickListener(this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == Activity.RESULT_OK){
                assert data != null;
                Workout new_workout = (Workout) data.getSerializableExtra("WORKOUT");
                database.mainDAO().insert(new_workout);
                list_workouts.clear();
                list_workouts.addAll(database.mainDAO().getAll());
                listAdapterWeekWorkouts.notifyDataSetChanged();
            }
        }
        if(requestCode == 102){
            if(resultCode == Activity.RESULT_OK){
                assert data != null;
                Workout new_workout = (Workout) data.getSerializableExtra("WORKOUT");
                database.mainDAO().update((int) new_workout.getId(), new_workout.getTitle(), new_workout.getWorkout_note(), new_workout.getDay());
                list_workouts.clear();
                list_workouts.addAll(database.mainDAO().getAll());
                listAdapterWeekWorkouts.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View view, int position) {
        Workout workout=list_workouts.get(position);
        Intent intent=new Intent(MainActivity.this, ActivityTrain.class);
        intent.putExtra("OLD_WORKOUT", workout);
        startActivityForResult(intent, 102);
    }
}