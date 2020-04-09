package com.example.imagegallery.api;


import com.example.imagegallery.models.FlickrImage;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;

public class Photos {

    @SerializedName("photo")
    @Expose
    private List<FlickrImage> photo = null;


    public List<FlickrImage> getPhotos() {
        return photo;
    }

}