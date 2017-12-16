package com.myanmar.tmn.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.viewHolder.ItemNewsViewHolder;

/**
 * Created by msi on 12/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsItems = inflater.inflate(R.layout.item_news,parent,false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItems);
        return itemNewsViewHolder;*/

        View newsItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,
                false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItems);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
