package com.example.admin.p0344444444444;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.TextView;

public class DataActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        long id = getIntent().getLongExtra("_id", -6);
        DBHelper dbhelper = new DBHelper(getBaseContext());
        SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();

        Cursor c = sqliteDB.query(Names.TABLE_NAME, null, BaseColumns._ID + "=" + id, null, null, null, null);

        TextView lv = (TextView) findViewById(R.id.response);
        lv.setBackgroundResource(R.drawable.list_item_shape);

        TextView tw = (TextView) findViewById(R.id.request);
        tw.setBackgroundResource(R.drawable.list_item_shape);

        if (c.moveToFirst()) {
            tw.setText(c.getString(c.getColumnIndex(Names.NamesColumns.NAME)));
            lv.setText(c.getString(c.getColumnIndex(Names.NamesColumns.AGE)));
        }
        dbhelper.close();
        sqliteDB.close();
    }
}