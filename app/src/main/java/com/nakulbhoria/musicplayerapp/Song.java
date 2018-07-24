package com.nakulbhoria.musicplayerapp;


import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable{
    private long id;
    private String name;
    private String artist;

    public Song(long songId, String SongName, String SongArtist) {
        this.id = songId;
        this.name = SongName;
        this.artist = SongArtist;
    }

    protected Song(Parcel in) {
        id = in.readLong();
        name = in.readString();
        artist = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(artist);
    }
}
