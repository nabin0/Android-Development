package com.myapp.practicerecyclerview;

public class ModelClass {
    private String mName;
    private String mAddress;
    private int mUserImage;

    public ModelClass(String mName, String mAddress, int mUserImage) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mUserImage = mUserImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getmUserImage() {
        return mUserImage;
    }

    public void setmUserImage(int mUserImage) {
        this.mUserImage = mUserImage;
    }
}
