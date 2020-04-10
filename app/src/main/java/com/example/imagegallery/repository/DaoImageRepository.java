package com.example.imagegallery.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.imagegallery.database.ImageDao;
import com.example.imagegallery.database.ImageDatabase;
import com.example.imagegallery.models.FlickrImage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DaoImageRepository {

    private ImageDao imageDao;
    private LiveData<List<FlickrImage>> allImages;
    private static final int insert = 1;
    private static final int deleteAll = 2;

    public DaoImageRepository(Application application){
        ImageDatabase database = ImageDatabase.getInstance(application);
        imageDao = database.imageDao();
        allImages = imageDao.getAllImages();
    }

    public void insert(FlickrImage note){
        new DbImageAsyncTask(imageDao, insert).execute(note);
    }

    public LiveData<List<FlickrImage>> getAllImages(){
        return allImages;
    }

    public void deleteAll(){
        new DbImageAsyncTask(imageDao, deleteAll).execute();
    }


    private static class DbImageAsyncTask extends AsyncTask<FlickrImage, Integer, Void> {
        private ImageDao imageDao;
        private Integer task;

        private DbImageAsyncTask(ImageDao imageDao, Integer task){
            this.imageDao = imageDao;
            this.task = task;
        }

        @Override
        protected Void doInBackground(FlickrImage... flickrImages) {
            switch(task){
                case 1:
                    imageDao.insert(flickrImages[0]);
                    return null;
                case 2:
                    imageDao.deleteAllImages();
                    return null;
                default:
                    return null;
            }
        }
    }

}
