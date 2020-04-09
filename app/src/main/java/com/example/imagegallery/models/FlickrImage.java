package com.example.imagegallery.models;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlickrImage implements Serializable {

    @SerializedName("id")
    @Expose
    private String imageId;
    @SerializedName("title")
    @Expose
    private String imageTitle;

    private String imageUrl;
    private String largeImageURL;
    private ImageView image;
    private ImageView fullScreenImage;

    public FlickrImage(String imageId, String imageTitle){
        this.setImageId(imageId);
        this.setImageTitle(imageTitle);
        this.imageUrl = "";
        this.largeImageURL = "";
        this.image = null;
        this.fullScreenImage = null;
    }





    public ImageView getLargeImage(){
        return fullScreenImage;
    }
    public void setLargeImage(ImageView image){
        this.fullScreenImage = image;
    }

    public ImageView getImage(){
        return image;
    }
    public void setImage(ImageView image){
        this.image = image;
    }

    public String getLargeImageURL() { return largeImageURL; }
    public void setLargeImageURL(String url) { this.largeImageURL = url;}

    public String getImageURL(){
        return imageUrl;
    }
    public void setImageURL(String val){
        this.imageUrl = val;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
