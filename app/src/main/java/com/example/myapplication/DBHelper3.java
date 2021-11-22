package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper3 extends SQLiteOpenHelper {
    /*데이터베이스 영역 따로 xml 필요없음.(subactivity구역 아님)*/
    static final String DATABASE_NAME = "test2.db";
    //static final int DATABASE_VERSION = 2;

    public DBHelper3(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }
    /*
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tableName3 (sum_tan DOUBLE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tableName3");
        onCreate(db);
    }





}
