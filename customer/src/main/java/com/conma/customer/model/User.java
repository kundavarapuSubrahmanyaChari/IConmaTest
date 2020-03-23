package com.conma.customer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String username;
    private String password;
    private String feature;

    public User(String username, String password, String feature) {
        this.username = username;
        this.password = password;
        this.feature = feature;
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        feature = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(feature);
    }
}
