package com.example.sportbook.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sportbook.Models.Workout;

@Database(entities = Workout.class, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;

    public synchronized static RoomDB getInstance(Context context){
        if(database==null){
            String DATABASE_NAME = "SportBook";
            database= Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract mainDAO mainDAO();
}
