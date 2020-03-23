package com.conma.itest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final String TABLE_NAME = "users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "usarname";
    public static final String COLUMN_PASSEOSRD = "password";
    public static final String COLUMN_FEATURE = "feature";

    private int id;
    private String username;
    private String password;
    private String feature;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_PASSEOSRD + " TEXT,"
                    + COLUMN_FEATURE + " TEXT"
                    + ")";

    public User() {
    }

    public User(int id, String username, String password, String feature) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.feature = feature;
    }
    public User(String username, String password, String feature) {
        this.username = username;
        this.password = password;
        this.feature = feature;
    }

    protected User(Parcel in) {
        id = in.readInt();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(feature);
    }
}
