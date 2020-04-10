package com.example.imagegallery.database;

import com.example.imagegallery.models.FlickrImage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ImageDao {

    @Insert
    void insert(FlickrImage img);

    @Update
    void update(FlickrImage img);

    @Delete
    void delete(FlickrImage img);

    @Query("DELETE FROM image_table")
    void deleteAllImages();

    @Query("SELECT * FROM image_table")
    LiveData<List<FlickrImage>> getAllImages();

}
