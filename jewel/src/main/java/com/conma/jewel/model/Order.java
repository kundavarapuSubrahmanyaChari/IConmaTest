package com.conma.jewel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    public static final String TABLE_NAME = "orders";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ITEMNAME = "ItemName";
    public static final String COLUMN_CATEGORY = "ItemCategory";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRICE = "Price";

    private int id;
    private String ItemName;
    private String ItemCategory;
    private String quantity;
    private String Price;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ITEMNAME + " TEXT,"
                    + COLUMN_CATEGORY + " TEXT,"
                    + COLUMN_QUANTITY + " TEXT,"
                    + COLUMN_PRICE + " TEXT"
                    + ")";

    public Order(int id, String itemName, String itemCategory, String quantity, String price) {
        this.id = id;
        ItemName = itemName;
        ItemCategory = itemCategory;
        this.quantity = quantity;
        Price = price;
    }

    public Order(String itemName, String itemCategory, String quantity, String price) {
        ItemName = itemName;
        ItemCategory = itemCategory;
        this.quantity = quantity;
        Price = price;
    }

    protected Order(Parcel in) {
        id = in.readInt();
        ItemName = in.readString();
        ItemCategory = in.readString();
        quantity = in.readString();
        Price = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCategory() {
        return ItemCategory;
    }

    public void setItemCategory(String itemCategory) {
        ItemCategory = itemCategory;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ItemName);
        dest.writeString(ItemCategory);
        dest.writeString(quantity);
        dest.writeString(Price);
    }
}
