package com.conma.jewel.salite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.conma.jewel.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "orders_db";
    public OrderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Order.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Order.TABLE_NAME);
        onCreate(db);
    }
    public long insertNote(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order.COLUMN_ITEMNAME, order.getItemName());
        values.put(Order.COLUMN_CATEGORY, order.getItemCategory());
        values.put(Order.COLUMN_QUANTITY, order.getQuantity());
        values.put(Order.COLUMN_PRICE, order.getPrice());
        long id = db.insert(Order.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public List<Order> getAllOrders() {
        Order order = null;
        List<Order> orders = new ArrayList<Order>();
        String selectQuery = "SELECT  * FROM " + Order.TABLE_NAME + " ORDER BY " +
                Order.COLUMN_ITEMNAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                order = new Order(
                        cursor.getInt(cursor.getColumnIndex(Order.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_ITEMNAME)),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_PRICE)));

                orders.add(order);
            }
        }

        db.close();

        return orders;
    }
    public int getOrdersCount() {
        String countQuery = "SELECT  * FROM " + Order.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }


}
