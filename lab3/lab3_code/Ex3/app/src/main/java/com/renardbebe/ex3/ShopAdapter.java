package com.renardbebe.ex3;

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
        ViewHolder holder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shoplistdetail, null);
            holder = new ViewHolder();
            holder.tv=(TextView) convertView.findViewById(R.id.icon);
            holder.tv1 = (TextView) convertView.findViewById(R.id.iname);
            holder.tv2 = (TextView) convertView.findViewById(R.id.iprice);
            convertView.setTag(holder);
        } else {
            convertView = view;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(carthings.get(position).getName().substring(0,1).toUpperCase());
        holder.tv1.setText(carthings.get(position).getName());
        holder.tv2.setText(carthings.get(position).getPrice());
        return convertView;
    }
    
    private class ViewHolder {
        public TextView tv;
        public TextView tv1;
        public TextView tv2;
    }
}
