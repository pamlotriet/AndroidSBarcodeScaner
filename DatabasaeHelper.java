package com.example.barcodescanner;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

 class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "products.db";
    public static final String TABLE_NAME = "product_data";

    //column variables
    public static final String col_1 = "Id";
    public static final String col_2 = "Name";
    public static final String col_3 = "Price";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(Id INTEGER PRIMARY KEY ,Name TEXT,Price TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(col_1,id);
        contentValue.put(col_2,name);
        contentValue.put(col_3,price);
        long result = db.insert(TABLE_NAME,null,contentValue);

        if (result != -1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public Cursor getData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE Id = id",null);
        return res;
    }
}
