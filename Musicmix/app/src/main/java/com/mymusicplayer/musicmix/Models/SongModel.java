package com.mymusicplayer.musicmix.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SongModel implements Serializable, Parcelable {

    // Useful attributes of songs as variable
    private String songName;
    private String albumName;
    private String artistName;
    private String songDuration;
    private String uriString;
    private String albumId;
    private String imagePath;
    private String audioPath;

    // Constructor of class
    public SongModel(String songName, String albumName, String artistName, String songDuration, String uriString, String albumId, String imagePath, String audioPath) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.songDuration = songDuration;
        this.uriString = uriString;
        this.albumId = albumId;
        this.imagePath = imagePath;
        this.audioPath = audioPath;
    }

    // Getters and setters to access data of this class

    protected SongModel(Parcel in) {
        songName = in.readString();
        albumName = in.readString();
        artistName = in.readString();
        songDuration = in.readString();
        uriString = in.readString();
        albumId = in.readString();
        imagePath = in.readString();
        audioPath = in.readString();
    }

    public static final Creator<SongModel> CREATOR = new Creator<SongModel>() {
        @Override
        public SongModel createFromParcel(Parcel in) {
            return new SongModel(in);
        }

        @Override
        public SongModel[] newArray(int size) {
            return new SongModel[size];
        }
    };

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeString(albumName);
        dest.writeString(artistName);
        dest.writeString(songDuration);
        dest.writeString(uriString);
        dest.writeString(albumId);
        dest.writeString(imagePath);
        dest.writeString(audioPath);
    }
}
