package com.renardbebe.ex3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renardbebe on 2017/10/23.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    private String Name;
    private String Price;
    private String Type;
    private String Info;

    private Context context;
    private Activity act;

    private int cnt = 0;

    public DetailAdapter(String Name, String Price, String Type, String Info, Context context, Activity act) {
        this.Name = Name;
        this.Price = Price;
        this.Type = Type;
        this.Info = Info;
        this.context = context;
        this.act = act;
    }

    public DetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.detail, parent, false);
        DetailAdapter.MyViewHolder holder = new DetailAdapter.MyViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // 接收信息
        final Intent intent = act.getIntent();
        act.setResult(1,intent);

        holder.tv1.setText(Name);
        holder.tv2.setText(Price);
        holder.tv3.setText(Type);
        holder.tv4.setText(Info);

        // 加载每个商品对应的图片
        if (Name.equals("Enchated Forest")) holder.img.setImageResource(R.drawable.pic1);
        else if (Name.equals("Arla Milk")) holder.img.setImageResource(R.drawable.pic2);
        else if (Name.equals("Devondale Milk")) holder.img.setImageResource(R.drawable.pic3);
        else if (Name.equals("Kindle Oasis")) holder.img.setImageResource(R.drawable.pic4);
        else if (Name.equals("waitrose 早餐麦片")) holder.img.setImageResource(R.drawable.pic5);
        else if (Name.equals("Mcvitie's 饼干")) holder.img.setImageResource(R.drawable.pic6);
        else if (Name.equals("Ferrero Rocher")) holder.img.setImageResource(R.drawable.pic7);
        else if (Name.equals("Maltesers")) holder.img.setImageResource(R.drawable.pic8);
        else if (Name.equals("Lindt")) holder.img.setImageResource(R.drawable.pic9);
        else if (Name.equals("Borggreve")) holder.img.setImageResource(R.drawable.pic10);

        // 添加商品到购物车
        holder.addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"商品已添加到购物车",Toast.LENGTH_SHORT).show();
                cnt++;

                intent.putExtra("cnt",cnt);
                intent.putExtra("name",Name);
                intent.putExtra("price",Price);
                intent.putExtra("type",Type);
                intent.putExtra("info",Info);
                // 回调
                act.setResult(0,intent);
            }
        });

        // 返回
        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.finish();
            }
        });

        // 星星转换
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag = holder.star.getTag();
                if(tag == "0"){
                    holder.star.setTag("1");
                    holder.star.setImageResource(R.mipmap.full_star);
                    Toast.makeText(context, "收藏成功!", Toast.LENGTH_SHORT).show();
                }
                else {
                    holder.star.setTag("0");
                    holder.star.setImageResource(R.mipmap.empty_star);
                    Toast.makeText(context, "你取消了收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 下拉列表
        final String[] message = new String[] {"一键下单","分享商品","不感兴趣","查看更多商品促销信息"};
        final List<Map<String, Object> > data = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Map<String, Object> tmp = new LinkedHashMap<>();
            tmp.put("message", message[i]);
            data.add(tmp);
        }
        String[] from = new String[] {"message"};
        int[] to = new int[] {R.id.message};
        final SimpleAdapter simpleAdapter = new SimpleAdapter(context, data, R.layout.item2, from, to);
        holder.listView.setAdapter(simpleAdapter);
    }

    @Override
    public int getItemCount() { return 1; }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3 ,tv4;
        ImageView img;
        ImageButton back, star, addcar;
        ListView listView;

        public MyViewHolder(View view) {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.d_name);
            tv2 = (TextView) view.findViewById(R.id.d_price);
            tv3 = (TextView) view.findViewById(R.id.d_type);
            tv4 = (TextView) view.findViewById(R.id.d_info);
            img = (ImageView) view.findViewById(R.id.pic);

            back = (ImageButton) view.findViewById(R.id.back);
            star = (ImageButton) view.findViewById(R.id.star);
            star.setTag("0");  // 初始为empty_star
            addcar = (ImageButton) view.findViewById(R.id.d_car);

            listView = (ListView) view.findViewById(R.id.message);
        }
    }
}
