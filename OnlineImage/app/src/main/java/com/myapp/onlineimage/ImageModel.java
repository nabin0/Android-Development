package com.myapp.onlineimage;

public class ImageModel {
    String imageuri;
    String imagename;

    public ImageModel(String imageuri, String imageTags) {
        this.imageuri = imageuri;
        this.imagename = imageTags;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getImageTags() {
        return imagename;
    }

    public void setImageTags(String imageTags) {
        this.imagename = imageTags;
    }
}
