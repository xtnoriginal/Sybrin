package com.example.sybrin.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhotoDetails.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDetailsDAO  photoDetailsDAO();
}