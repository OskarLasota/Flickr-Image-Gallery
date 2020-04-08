package com.example.imagegallery.models;

import android.widget.ImageView;

public class FlickrImage {


    private String imageId;
    private String imageTitle;
    private String imageUrl;
    private ImageView image;

    public FlickrImage(String imageId, String imageTitle){
        this.setImageId(imageId);
        this.setImageTitle(imageTitle);
        this.imageUrl = "";
        this.image = null;
    }

    public ImageView getImage(){
        return image;
    }
    public void setImage(ImageView image){
        this.image = image;
    }

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
