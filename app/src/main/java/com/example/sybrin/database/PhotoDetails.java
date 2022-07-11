package com.example.sybrin.database;


import android.media.Image;

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

    @ColumnInfo(name = "picture")
    public Image picture;


}

