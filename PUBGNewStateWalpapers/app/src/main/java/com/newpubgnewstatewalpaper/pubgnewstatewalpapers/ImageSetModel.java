package com.newpubgnewstatewalpaper.pubgnewstatewalpapers;

public class ImageSetModel {
    String imageUrl;
    String imageName;

    public ImageSetModel(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
