package com.example.imagegallery.viewmodels;

import com.example.imagegallery.models.FlickrImage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<FlickrImage>> mImages;

    public LiveData<List<FlickrImage>> getImages(){
        return mImages;
    }

}
