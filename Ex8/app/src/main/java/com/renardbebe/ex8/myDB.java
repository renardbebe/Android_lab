package com.renardbebe.ex8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by renardbebe on 2017/12/6.
 */

public class myDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "Contacts.db";
    private static final String TABLE_NAME = "Contacts";
    private static final int DB_VERSION = 1;

    public myDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME
                + " (_id integer primary key , "
                + "name text , "
                + "birth text , "
                + "gift text);";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void insert(String name, String birth, String gift) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("birth", birth);
        values.put("gift", gift);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void update(String where, String[] args, String name, String birth, String gift) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = where+" = ?";  // 满足该whereClause子句的记录将会被更新
        String whereArgs[] = args;          // 用于为whereArgs子句传递参数
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("birth", birth);
        values.put("gift", gift);
        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public void delete(String where, String[] args){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = where+" = ?";
        String[] whereArgs = args;

        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public Cursor getAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id","name","birth","gift"},
                null,null,null,null,null);
        return cursor;
    }

    public boolean queryByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{"name"},
                "name = ?",new String[]{name},null,null,null);
        int a = cursor.getCount();
        Log.e("a",a+"");
        return (a>0);
    }
}
