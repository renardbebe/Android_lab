package com.renardbebe.ex3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renardbebe on 2017/10/18.
 */

public class detail extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv);

        mRecyclerView = (RecyclerView) findViewById(R.id.r);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 接收信息
        Intent intent = getIntent();
        final String Name = intent.getStringExtra("Name");
        final String Price = intent.getStringExtra("Price");
        final String Type = intent.getStringExtra("Type");
        final String Info = intent.getStringExtra("Info");

        DetailAdapter detailAdapter = new DetailAdapter(Name, Price, Type, Info, detail.this, detail.this);
        mRecyclerView.setAdapter(detailAdapter);
    }
}
