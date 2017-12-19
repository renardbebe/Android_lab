package com.renardbebe.ex5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by renardbebe on 2017/10/18.
 */

public class detail extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.renardbebe.ex5.R.layout.rv);

        mRecyclerView = (RecyclerView) findViewById(com.renardbebe.ex5.R.id.r);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String Name="a", Price="b", Type="c", Info="d";
        // 接收信息
        Intent intent = getIntent();
        if(intent != null) {
            Name = intent.getStringExtra("Name");
            Price = intent.getStringExtra("Price");
            Type = intent.getStringExtra("Type");
            Info = intent.getStringExtra("Info");
        }

        DetailAdapter detailAdapter = new DetailAdapter(Name, Price, Type, Info, detail.this, detail.this);
        mRecyclerView.setAdapter(detailAdapter);
    }
}
