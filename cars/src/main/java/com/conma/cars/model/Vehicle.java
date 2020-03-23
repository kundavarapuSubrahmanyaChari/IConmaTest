package com.conma.cars.model;

import java.util.ArrayList;


public class Vehicle {
    private String brand;
    private String model;
    private String price;
    private String description;
    private String year;
    private int thumbnailID;
    private ArrayList<Integer> images;

    public Vehicle() {

    }

    public Vehicle(String brand, String model, String year, String price, String description, int thumbnailID, ArrayList<Integer> images) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.description = description;
        this.thumbnailID = thumbnailID;
        this.images = images;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public int getThumbnailID() {
        return thumbnailID;
    }


    public void setThumbnailID(int thumbnailID) {
        this.thumbnailID = thumbnailID;
    }


    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }
}
