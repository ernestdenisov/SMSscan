package com.example.admin.p0344444444444;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Names.TABLE_NAME + " (" + BaseColumns._ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + Names.NamesColumns.NAME
                + " TEXT , " + Names.NamesColumns.AGE + " TEXT, "
                + Names.NamesColumns.FNAME + " TEXT  );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}