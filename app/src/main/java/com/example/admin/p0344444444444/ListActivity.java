package com.example.admin.p0344444444444;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Admin on 30.09.15.
 */
public class ListActivity extends Activity {

    final Context context = this;
    int rowId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        DBHelper dbhelper = new DBHelper(getBaseContext());
        SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();

        final String[] from = {Names.NamesColumns.FNAME, BaseColumns._ID};
        final Cursor c = sqliteDB.query(Names.TABLE_NAME, null, null, null, null, null,
                Names.DEFAULT_SORT);
        final int i = c.getCount();
        final int[] to = new int[]{R.id.text1};

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.list, c, from, to);
        final ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setBackgroundResource(R.drawable.list_item_shape);
        lv.setAdapter(adapter);

        //go to DataActivity with SMS content
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(ListActivity.this, DataActivity.class);
                intent.putExtra("_id", id);
                startActivity(intent);
                finish();
            }
        });

        //long click helps delete old SMS from app DateBase
        //You can fill it with more function (edit, rename etc.)
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {

                final CharSequence[] items = {"Delete"};
                AlertDialog.Builder builder3 = new AlertDialog.Builder(ListActivity.this);

                builder3.setTitle("You can delete this message").setItems(items,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int item) {

                                switch (item) {
                                    case 0: {
                                        DBHelper dbhelper = new DBHelper(getBaseContext());
                                        SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
                                        ManController.delete(getBaseContext(), adapter.getItemId(pos));
                                        final Cursor c = sqliteDB.query(Names.TABLE_NAME, null, null, null, null, null,
                                                Names.DEFAULT_SORT);
                                        adapter.changeCursor(c);
                                        dbhelper.close();
                                        sqliteDB.close();
                                    }
                                    break;
                                }
                            }
                        });
                builder3.show();
                return true;
            }
        });
        dbhelper.close();
        sqliteDB.close();
    }
}
