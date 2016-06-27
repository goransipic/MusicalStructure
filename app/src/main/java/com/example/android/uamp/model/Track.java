package com.example.android.uamp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by goransi on 27.6.2016..
 */
public class Track implements Parcelable {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("id")
    private int mID;

    @SerializedName("stream_url")
    private String mStreamURL;

    @SerializedName("artwork_url")
    private String mArtworkURL;

    @SerializedName("description")
    private String mDescription;

    public String getTitle() {
        return mTitle;
    }

    public String getStreamURL() {
        return mStreamURL;
    }

    public String getArtworkURL() {
        return mArtworkURL;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    protected Track(Parcel in) {
        mTitle = in.readString();
        mID = in.readInt();
        mStreamURL = in.readString();
        mArtworkURL = in.readString();
        mDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mID);
        dest.writeString(mStreamURL);
        dest.writeString(mArtworkURL);
        dest.writeString(mDescription);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
}
