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
    private int dbid;

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
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        this.imageUrl = "";
        this.largeImageURL = "";
        this.largeImageByte = null;
        this.imageByte = null;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
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
}
