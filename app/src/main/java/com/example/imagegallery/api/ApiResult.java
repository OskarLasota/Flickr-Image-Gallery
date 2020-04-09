package com.example.imagegallery.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResult {

    @SerializedName("photos")
    @Expose
    private Photos photos;


    public Photos getPhotos() {
        return photos;
    }


}