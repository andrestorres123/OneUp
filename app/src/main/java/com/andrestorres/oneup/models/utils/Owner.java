package com.andrestorres.oneup.models.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrestorres on 12/15/16.
 */

public class Owner implements Parcelable {
    private String login;
    private int id;
    @SerializedName("avatar_url")
    private String profilePic;

    protected Owner(Parcel in) {
        login = in.readString();
        id = in.readInt();
        profilePic = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeInt(id);
        parcel.writeString(profilePic);
    }
}
