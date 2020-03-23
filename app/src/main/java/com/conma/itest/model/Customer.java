package com.conma.itest.model;

public class Customer {
    String userName;
    String password;
    String feature;

    public Customer(String userName, String password, String feature) {
        this.userName = userName;
        this.password = password;
        this.feature = feature;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
