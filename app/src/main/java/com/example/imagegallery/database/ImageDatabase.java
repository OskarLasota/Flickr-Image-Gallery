package com.example.imagegallery.database;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;

import com.example.imagegallery.models.FlickrImage;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//singleton
@Database(entities = {FlickrImage.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {
    private static ImageDatabase instance;
    public abstract ImageDao imageDao();

    //synchronized allowing only 1 thread to access to prevent multiple being created
    public static synchronized ImageDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ImageDatabase.class, "image_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
