package com.myanmar.tmn.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.viewHolder.ItemsInternationalNewsViewHolder;

/**
 * Created by msi on 1/11/2018.
 */

public class ItemsInternationalNewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View items = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                items_international_news,parent,false);
        ItemsInternationalNewsViewHolder itemsInternationalNewsViewHolder = new
                ItemsInternationalNewsViewHolder(items);
        return itemsInternationalNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
