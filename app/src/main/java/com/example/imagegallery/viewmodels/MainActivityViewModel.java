package com.example.imagegallery.viewmodels;

import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.repository.FlickrImageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<FlickrImage>> mImages;
    private FlickrImageRepository repo;


    public void init(){
        if(mImages != null){
            return;
        }
        repo = FlickrImageRepository.getInstance();
        mImages = repo.getImages();
    }

    public LiveData<List<FlickrImage>> getImages(){
        return mImages;
    }

}
