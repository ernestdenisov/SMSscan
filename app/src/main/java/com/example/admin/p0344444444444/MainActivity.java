package com.example.admin.p0344444444444;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Application application = getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) new DefaultExceptionHandler(this));

        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setBackgroundResource(R.drawable.list_item_shape);

        //go to SMS list
        Button btn = (Button) findViewById(R.id.button2);
        btn.setBackgroundResource(R.drawable.list_item_shape);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
