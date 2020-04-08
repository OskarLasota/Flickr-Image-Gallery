package com.example.imagegallery.repository;

import com.example.imagegallery.R;
import com.example.imagegallery.models.FlickrImage;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class FlickrImageRepository {

    private static FlickrImageRepository instance;
    private List<FlickrImage> dataSet = new ArrayList<>();

    public static FlickrImageRepository getInstance(){
        if(instance == null){
            instance = new FlickrImageRepository();
        }
        return instance;
    }

    public MutableLiveData<List<FlickrImage>> getImages(){
        setImages();
        MutableLiveData<List<FlickrImage>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setImages(){
        //get all the images from the api
        dataSet.add(new FlickrImage("test id", "title1", R.drawable.ic_launcher_background));
        dataSet.add(new FlickrImage("test id", "title2", R.drawable.ic_launcher_background));

    }

}
