package com.example.sportbook.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "Workout")
public class Workout implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long ID = 0;

    @ColumnInfo(name = "day")
    public String day = "";

    @ColumnInfo(name = "title")
    public String title = "";

    @ColumnInfo(name = "workout_note")
    public String workout_note = "";

    public long getId() {
        return ID;
    }

    public void setId(long id) {
        this.ID = id;
    }

    public String getDay() {
        return day;
    }

    public String getWorkout_note() {
        return workout_note;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setWorkout_note(String workout_note) {
        this.workout_note = workout_note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
