package com.nabin.blog.BlogData;

import android.os.Parcel;
import android.os.Parcelable;

import com.nabin.blog.BlogHttpClient;

public class Author implements Parcelable {
    private String name;
    private String avatar;

    protected Author(Parcel in) {
        name = in.readString();
        avatar = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAvatarUrl(){
        return BlogHttpClient.BASE_URL + BlogHttpClient.PATH + getAvatar();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(avatar);
    }
}
