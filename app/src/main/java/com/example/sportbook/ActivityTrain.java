package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportbook.Models.Workout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityTrain extends AppCompatActivity {

    Button but_day_workout;
    EditText editText_workout, editText_title;
    ExtendedFloatingActionButton extendedFab_save;
    Workout workout;
    boolean isOldWorkout = false;
    String date_workout = "";
    DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        Init();
    }

    public void Init(){
        but_day_workout = findViewById(R.id.button_day_workout);
        editText_workout=findViewById(R.id.edittext_workout);
        editText_title=findViewById(R.id.edittext_title);
        extendedFab_save=findViewById(R.id.fab_save_train);
        dateFormat=new SimpleDateFormat("dd.MM", Locale.getDefault());

        workout=new Workout();
        try{
            workout = (Workout) getIntent().getSerializableExtra("OLD_WORKOUT");
            editText_workout.setText(workout.getWorkout_note());
            but_day_workout.setText(workout.getDay());
            editText_title.setText(workout.getTitle());
            isOldWorkout=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        but_day_workout.setOnClickListener(v -> {
            Calendar calendar=Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        date_workout = dateFormat.format(calendar.getTime());
                        but_day_workout.setText(date_workout);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        extendedFab_save.setOnClickListener(v -> {
            String workout_note=editText_workout.getText().toString();
            String workout_title=editText_title.getText().toString();
            if(workout_note.isEmpty()){
                Toast.makeText(this, "Please, write your workout!", Toast.LENGTH_LONG).show();
                return;
            }
            if(!isOldWorkout){
                workout=new Workout();
            }
            workout.setWorkout_note(workout_note);
            workout.setTitle(workout_title);
            workout.setDay(date_workout);
            Intent intent=new Intent();
            intent.putExtra("WORKOUT", workout);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}