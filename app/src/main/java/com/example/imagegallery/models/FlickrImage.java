package com.example.imagegallery.models;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image_table")
public class FlickrImage implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private String imageId;
    @SerializedName("title")
    @Expose
    private String imageTitle;

    private String imageUrl;
    private String largeImageURL;

    private byte[] imageByte;
    private byte[] largeImageByte;


    public FlickrImage(String imageId, String imageTitle){
        this.setImageId(imageId);
        this.setImageTitle(imageTitle);
        this.imageUrl = "";
        this.largeImageURL = "";
        this.largeImageByte = null;
        this.imageByte = null;
    }


    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public byte[] getLargeImageByte() {
        return largeImageByte;
    }

    public void setLargeImageByte(byte[] largeImageByte) {
        this.largeImageByte = largeImageByte;
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
