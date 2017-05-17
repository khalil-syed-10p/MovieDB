package com.khalil.moviedb.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created on 5/17/17.
 */

public class MovieEntity implements Parcelable {

    public static final String KEY_EXTRA = "Movie";

    String posterPath;
    String title;

    public String getPosterPath() {
        return TextUtils.isEmpty(posterPath) ? "" : ("https://image.tmdb.org/t/p/w640" + posterPath);
    }

    public String getTitle() {
        return TextUtils.isEmpty(title) ? "No Title Available" : title;
    }

    public MovieEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
    }

    protected MovieEntity(Parcel in) {
        this.posterPath = in.readString();
        this.title = in.readString();
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
