package com.lin.timeline.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by lin18 on 2017/8/23.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void timeline(View view) {
        startActivity(new Intent(this, TimeLineActivity.class));
    }

    public void order(View view) {
        startActivity(new Intent(this, OrderActivity.class));
    }

    public void trace(View view) {
        startActivity(new Intent(this, TraceActivity.class));
    }
}
