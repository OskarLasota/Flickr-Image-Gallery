package com.example.imagegallery.api;

import com.example.imagegallery.models.FlickrImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET
    Call<ApiResult> getApiImages(@Url String url);

}
