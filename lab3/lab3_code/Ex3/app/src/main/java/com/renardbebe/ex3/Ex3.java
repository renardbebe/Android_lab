package com.renardbebe.ex3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ex3 extends AppCompatActivity {

    // 主页
    protected RecyclerView mRecyclerView;
    protected List<Data> list = new ArrayList<Data>();
    protected HomeAdapter homeAdapter;

    // 购物车
    protected ListView mListView;
    protected List<Data> carlist = new ArrayList<Data>();
    protected ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        final String[] Name = new String[]{"Enchated Forest", "Arla Milk", "Devondale Milk", "Kindle Oasis", "waitrose 早餐麦片",
                                            "Mcvitie's 饼干", "Ferrero Rocher", "Maltesers", "Lindt", "Borggreve"};
        final String[] Price = new String[]{"¥ 5.00", "¥ 59.00", "¥ 79.00", "¥ 2399.00", "¥ 179.00",
                                            "¥ 14.00", "¥ 132.59", "¥ 141.43", "139.43", "28.90"};
        final String[] Type = new String[]{"作者", "产地", "产地", "版本", "重量",
                                            "产地", "重量", "重量", "重量", "重量"};
        final String[] Info = new String[]{"Johanna Basford", "德国", "澳大利亚", "8GB", "2Kg",
                                            "英国", "300g", "118g", "249g", "640g"};

        for(int i = 0; i < 10; i++) {
            list.add(new Data(Name[i], Price[i], Type[i], Info[i]));
        }
        /* 主页商品列表 */
        mRecyclerView = (RecyclerView) findViewById(R.id.shop);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter(list, Ex3.this);
        mRecyclerView.setAdapter(homeAdapter);

        /* 添加动画效果 */
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final String [] items = {"详细信息", "商品图片"};

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener(){
            @Override
            /* 显示商品详情 */
            public void onClick(final int position) {
                alertDialog.setTitle("请选择需要展示的内容：").setItems(items,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(i == 0) {
                                    Intent intent = new Intent(Ex3.this, detail.class);
                                    intent.putExtra("Name", list.get(position).getName());
                                    intent.putExtra("Price", list.get(position).getPrice());
                                    intent.putExtra("Type", list.get(position).getType());
                                    intent.putExtra("Info", list.get(position).getInfo());
                                    startActivityForResult(intent,1);
                                }
                                else {
                                    Intent intent = new Intent(Ex3.this, showPicture.class);
                                    intent.putExtra("Name", list.get(position).getName());
                                    startActivity(intent);
                                }
                            }
                        }).create();
                alertDialog.show();
            }

            @Override
            /* 删除商品 */
            public void onLongClick(int position) {
                Toast.makeText(Ex3.this,"移除第" + String.valueOf(position+1) + "个商品",Toast.LENGTH_SHORT).show();
                list.remove(position);
                homeAdapter.notifyDataSetChanged();
            }
        });

        /* 购物车界面 */
        final LinearLayout shoplist = (LinearLayout) findViewById(R.id.shoplist);
        shoplist.setVisibility(View.INVISIBLE);

        mListView = (ListView) findViewById(R.id.caritem);
        shopAdapter = new ShopAdapter(carlist, Ex3.this);
        mListView.setAdapter(shopAdapter);
        /*carlist.add(new Data("A", "B", "C", "D"));*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            /* 显示商品详情 */
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l){
                Intent intent = new Intent(Ex3.this, detail.class);
                intent.putExtra("Name", carlist.get(position).getName());
                intent.putExtra("Price", carlist.get(position).getPrice());
                intent.putExtra("Type", carlist.get(position).getType());
                intent.putExtra("Info", carlist.get(position).getInfo());
                startActivityForResult(intent,1);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            /* 从购物车中移除商品 */
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Ex3.this);
                alertDialog.setTitle("移除商品").setMessage("从购物车移除" + carlist.get(position).getName() + "?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (carlist.remove(position) != null )
                            shopAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "成功移除!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "你选择了[取消]", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                return true;
            }
        });

        /* 悬浮按键 */
        final ImageButton f = (ImageButton) findViewById(R.id.floating);
        f.setTag("0");
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f.getTag() == "0") {
                    f.setTag("1");
                    f.setImageResource(R.mipmap.home);
                    shoplist.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    f.setTag("0");
                    f.setImageResource(R.mipmap.car);
                    shoplist.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // 向购物车中添加商品
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(resultCode == 0) {
            Bundle bud = intent.getExtras();
            String name = bud.getString("name");
            String price = bud.getString("price");
            String type = bud.getString("type");
            String info = bud.getString("info");
            int cnt = bud.getInt("cnt",0);
            for(int i = 0; i < cnt; i++){
                carlist.add(new Data(name, price, type, info));
            }
            shopAdapter.notifyDataSetChanged();
        }
    }
}
