package com.example.imagegallery.models;

public class FlickrImage {


    private String imageId;
    private String imageTitle;
    private int drawableid;

    public FlickrImage(String imageId, String imageTitle, int drawableid){
        this.setImageId(imageId);
        this.setImageTitle(imageTitle);
        this.drawableid = drawableid;
    }


    public int getDrawableId(){
        return drawableid;
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
