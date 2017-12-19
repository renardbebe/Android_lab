package com.renardbebe.ex5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by renardbebe on 2017/10/22.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<Data> data;
    private Context context;

    public HomeAdapter(List<Data> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(com.renardbebe.ex5.R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position){
        holder.name.setText(data.get(position).getName());
        holder.firstletter.setText(data.get(position).getName().substring(0,1).toUpperCase());

        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                /* 获取Item位置 */
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount(){ return data.size(); }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView firstletter;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(com.renardbebe.ex5.R.id.name);
            firstletter = (TextView) view.findViewById(com.renardbebe.ex5.R.id.firstletter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
