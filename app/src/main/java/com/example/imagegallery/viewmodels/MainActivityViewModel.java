package com.example.imagegallery.viewmodels;

import android.os.AsyncTask;

import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.repository.FlickrImageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private FlickrImageRepository repo;
    private MutableLiveData<List<FlickrImage>> mImages;
    private MutableLiveData<Boolean> isUpdating;


    public void init(){
        if(mImages != null){
            return;
        }
        isUpdating = new MutableLiveData<>();
        repo = FlickrImageRepository.getInstance();
        mImages = repo.getEntries();
        isUpdating = repo.getProcess();
    }



    public LiveData<List<FlickrImage>> getImages(){
        return mImages;
    }

    public LiveData<Boolean> getIsUpdating(){
        return isUpdating;
    }
}
