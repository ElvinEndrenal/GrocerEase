package com.example.grocerease;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    public static final String GROCERY_TABLE = "groceryList";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_GROCERY_NAME = "GroceryName";
    public static final String COLUMN_GROCERY_PRICE = "GroceryPrice";
    public static final String COLUMN_GROCERY_QUANTITY = "GroceryQuantity";

    public dbHelper(@Nullable Context context){
        super(context, "grocery.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + GROCERY_TABLE + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GROCERY_NAME + " TEXT," + COLUMN_GROCERY_PRICE + " INT,"
                + COLUMN_GROCERY_QUANTITY + " INT)";

                db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddOne(listModel listmodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GROCERY_NAME, listmodel.getGroceryName());
        cv.put(COLUMN_GROCERY_PRICE, listmodel.getPrice());
        cv.put(COLUMN_GROCERY_QUANTITY, listmodel.getQuantity());

        long insert = db.insert(GROCERY_TABLE, null, cv);

        return insert != -1;
    }

    public List<listModel> getAll(){
        List<listModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + GROCERY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                int groceryID = cursor.getInt(0);
                String groceryName = cursor.getString(1);
                int groceryPrice = cursor.getInt(2);
                int groceryQuantity = cursor.getInt((3));
            }
        } else {
            //none
        }

        db.close();
        cursor.close();
        return returnList;
    }

    public boolean DeleteOne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(GROCERY_TABLE, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean UpdateOne(listModel listmodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GROCERY_NAME, listmodel.getGroceryName());
        cv.put(COLUMN_GROCERY_PRICE, listmodel.getPrice());
        cv.put(COLUMN_GROCERY_QUANTITY, listmodel.getQuantity());

        int result = db.update(GROCERY_TABLE, cv, COLUMN_ID + "= ?", new String[]{String.valueOf(listmodel.getID())});
        return result > 0;
    }

}
