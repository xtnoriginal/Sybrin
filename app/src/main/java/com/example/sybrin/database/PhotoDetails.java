package com.example.sybrin.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class PhotoDetails {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "contents")
    public String contents ;


}

