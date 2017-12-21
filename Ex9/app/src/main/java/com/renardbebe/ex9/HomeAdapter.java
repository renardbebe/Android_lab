package com.renardbebe.ex9;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.renardbebe.ex9.model.Github;

import java.util.List;

/**
 * Created by renardbebe on 2017/12/13.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<Github> data;
    private Context context;

    public HomeAdapter(List<Github> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position){
        holder.name.setText(data.get(position).getName());
        holder.id.setText(data.get(position).getId());
        holder.blog.setText(data.get(position).getBlog());

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
        TextView id;
        TextView blog;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_name);
            id = (TextView) view.findViewById(R.id.item_id);
            blog = (TextView) view.findViewById(R.id.item_blog);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

