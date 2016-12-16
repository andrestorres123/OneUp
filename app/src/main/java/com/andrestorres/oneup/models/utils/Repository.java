package com.andrestorres.oneup.models.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by andrestorres on 12/15/16.
 */

public class Repository implements Parcelable {

    private int id;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    private Owner owner;
    private String url;
    @SerializedName("created_at")
    private String createdAt;

    protected Repository(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fullName = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
        url = in.readString();
        createdAt = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(fullName);
        parcel.writeParcelable(owner, i);
        parcel.writeString(url);
        parcel.writeString(createdAt);
    }
}
