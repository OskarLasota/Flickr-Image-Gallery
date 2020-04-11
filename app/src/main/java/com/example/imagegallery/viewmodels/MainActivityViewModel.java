package com.example.imagegallery.viewmodels;

import android.app.Application;
import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.repository.DaoImageRepository;
import com.example.imagegallery.repository.FlickrImageRepository;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private FlickrImageRepository repo;
    private LiveData<List<FlickrImage>> allImages;
    private LiveData<Boolean> isApiProcessing;
    private DaoImageRepository imageRepository;


    //using AndroidViewModel to make Room database work
    public MainActivityViewModel(Application application){
        super(application);
        imageRepository = new DaoImageRepository(application);
        isApiProcessing = new MutableLiveData<>();
        repo = FlickrImageRepository.getInstance();
        allImages = imageRepository.getAllImages();
    }



    public void apiGetImages(){
        imageRepository.deleteAll();
        allImages = repo.getEntries();
        isApiProcessing = repo.getProcess();
    }

    public void dbStoreImages(){
        for(FlickrImage img : allImages.getValue()){
            imageRepository.insert(img);
        }
    }

    public LiveData<List<FlickrImage>> getImages(){
        return allImages;
    }

    public LiveData<Boolean> getIsApiProcessing(){
        return isApiProcessing;
    }
}
