package com.nirodha.stopwatch;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {



    public databaseHelper(Context context){
        super(context,"mydb5",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql ="CREATE TABLE table1 (_id INTEGER PRIMARY KEY AUTOINCREMENT,minute INTEGER,second INTEGER,decimal INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

