package com.myanmar.tmn.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.viewHolder.ItemsMainNewsViewHolder;

/**
 * Created by msi on 1/19/2018.
 */

public class ItemsMainNewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View items = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_international_news,parent,false);
        ItemsMainNewsViewHolder viewHolder = new ItemsMainNewsViewHolder(items);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
