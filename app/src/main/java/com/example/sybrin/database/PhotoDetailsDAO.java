package com.example.sybrin.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhotoDetailsDAO{

    @Query("SELECT * FROM photodetails WHERE uid IN (:userIds)")
    List<PhotoDetails> loadAllByIds(int[] userIds);


    @Insert
    void insertAll(PhotoDetails... photos);

    @Delete
    void delete(PhotoDetails photoDetails);
}