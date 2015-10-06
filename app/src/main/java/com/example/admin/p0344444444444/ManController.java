package com.example.admin.p0344444444444;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class ManController {

    private static int maxRowsInNames = -1;

    private ManController() {

    }

    public static int getMaxRowsInNames() {

        return maxRowsInNames;
    }

    public static ArrayList<Names> readNames(Context context) {

        ArrayList<Names> list = null;
        try {
            DBHelper dbhelper = new DBHelper(context);
            SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
            String[] columnsToTake = { BaseColumns._ID, Names.NamesColumns.FNAME };
            Cursor cursor = sqliteDB.query(Names.TABLE_NAME, columnsToTake, null, null, null, null,
                    Names.DEFAULT_SORT);
            if (cursor.moveToFirst()) {
                list = new ArrayList<Names>();
            }
            while (cursor.moveToNext()) {
                Names oneRow = new Names();
                oneRow.setId(cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                oneRow.setFname(cursor.getString(cursor.getColumnIndexOrThrow(Names.NamesColumns.FNAME)));
                list.add(oneRow);
            }
            cursor.close();
            dbhelper.close();
        } catch (Exception e) {}
        return list;
    }

    public static void setMaxRowsInNames(int maxRowsInNames) {

        ManController.maxRowsInNames = maxRowsInNames;
    }

    public static void delete(Context context, long l) {

        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.delete(Names.TABLE_NAME, BaseColumns._ID  + " = " + l, null);
        sqliteDB.close();
        dbhelper.close();
    }

    public static void write(Context context, String name, String fname, String age) {

        try {
            //создали нашу базу и открыли для записи
            DBHelper dbhelper = new DBHelper(context);
            SQLiteDatabase sqliteDB = dbhelper.getWritableDatabase();
            String quer = null;
            int countRows = -1;

            Cursor cursor = sqliteDB.query(Names.TABLE_NAME, new String[] { "count(*)" }, null, null, null,
                    null, Names.DEFAULT_SORT);
            if (cursor.moveToFirst()) {
                countRows = cursor.getInt(0);
            }
            cursor.close();
            if ((maxRowsInNames == -1) || (maxRowsInNames >= countRows)) {

                quer = String.format("INSERT INTO %s (%s, %s, %s) VALUES (%s, %s, %s);",
                        // таблица
                        Names.TABLE_NAME,
                        // колонки
                        Names.NamesColumns.NAME, Names.NamesColumns.AGE,
                        Names.NamesColumns.FNAME,
                        // поля
                        name, age, fname);
            }

            sqliteDB.execSQL(quer);
            sqliteDB.close();
            dbhelper.close();
        } catch (SQLiteException e) {}
        catch (SQLException e) {}
    }
}