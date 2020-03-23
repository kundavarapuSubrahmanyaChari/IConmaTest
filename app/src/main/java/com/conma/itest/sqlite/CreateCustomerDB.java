package com.conma.itest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.conma.itest.model.Customer;
import com.conma.itest.model.User;

import java.util.ArrayList;
import java.util.List;

public class CreateCustomerDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db";
    public CreateCustomerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }
    public long insertNote(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_USERNAME, user.getUsername());
        values.put(User.COLUMN_PASSEOSRD, user.getPassword());
        values.put(User.COLUMN_FEATURE, user.getFeature());
        long id = db.insert(User.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public User getUser(String userName) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_USERNAME, User.COLUMN_PASSEOSRD, User.COLUMN_FEATURE},
                User.COLUMN_USERNAME + "=?",
                new String[]{userName}, null, null, null, null);

        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                 user = new User(
                        cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSEOSRD)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_FEATURE)));

//                cursor.close();
            }
        }


        return user;
    }

    public List<User> getAllUsers() {
        User user = null;
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_USERNAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                 user = new User(
                        cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSEOSRD)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_FEATURE)));

                users.add(user);
            }
        }

        db.close();

        return users;
    }
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}