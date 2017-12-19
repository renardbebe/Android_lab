package com.renardbebe.ex5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by renardbebe on 2017/10/22.
 */

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<Data> carthings;

    public ShopAdapter(List<Data> carthings, Context context) {
        this.carthings = carthings;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (carthings != null) return carthings.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        if (carthings != null) return carthings.get(i);
        else return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View convertView;
        MyViewHolder holder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(com.renardbebe.ex5.R.layout.shoplistdetail, null);
            holder = new MyViewHolder();
            holder.firstletter = (TextView) convertView.findViewById(com.renardbebe.ex5.R.id.icon);
            holder.name = (TextView) convertView.findViewById(com.renardbebe.ex5.R.id.iname);
            holder.price = (TextView) convertView.findViewById(com.renardbebe.ex5.R.id.iprice);
            convertView.setTag(holder);
        } else {
            convertView = view;
            holder = (MyViewHolder) convertView.getTag();
        }
        if(carthings.get(position).getName() != "购物车") {
            holder.firstletter.setText(carthings.get(position).getName().substring(0,1).toUpperCase());
            holder.name.setText(carthings.get(position).getName());
            holder.price.setText(carthings.get(position).getPrice());
            holder.name.setTextSize(18);
            holder.price.setTextSize(18);
        }
        else {
            holder.firstletter.setText("*");
            holder.firstletter.setTextSize(25);
            holder.name.setText(carthings.get(position).getName());
            holder.price.setText(carthings.get(position).getPrice());
            holder.name.setTextSize(25);
            holder.price.setTextSize(25);
        }
        return convertView;
    }
    
    private class MyViewHolder {
        public TextView firstletter;
        public TextView name;
        public TextView price;
    }
}
