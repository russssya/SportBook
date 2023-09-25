package com.example.sportbook.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sportbook.Models.Workout;

import java.util.List;

@Dao
public interface mainDAO {

    @Insert(onConflict = REPLACE)
    void insert(Workout workout);

    @Query("SELECT * FROM workout ORDER BY id DESC")
    List<Workout> getAll();

    @Query("UPDATE workout SET title = :title, day = :day, workout_note = :workout_note WHERE ID = :id")
    void update(int id, String title, String workout_note, String day);

    @Delete
    void delete(Workout workout);
}
