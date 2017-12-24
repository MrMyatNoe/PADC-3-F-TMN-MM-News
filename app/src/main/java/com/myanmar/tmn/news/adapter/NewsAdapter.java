package com.myanmar.tmn.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.NewsVO;
import com.myanmar.tmn.news.delegates.NewsActionDelegate;
import com.myanmar.tmn.news.viewHolder.ItemNewsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 12/3/2017.
 */

//link with itemsNewsViewHolder, using generic
public class NewsAdapter extends RecyclerView.Adapter<ItemNewsViewHolder> {

    private NewsActionDelegate newsActionDelegate;

    //To show data dynamically
    private List<NewsVO> mNewsList;

    public NewsAdapter(NewsActionDelegate actionDelegate) {
        newsActionDelegate = actionDelegate;
        mNewsList = new ArrayList<>();
    }

    @Override
    public ItemNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsItems = inflater.inflate(R.layout.item_news,parent,false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItems);
        return itemNewsViewHolder;*/

        View newsItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,
                false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItems, newsActionDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemNewsViewHolder holder, int position) {
        holder.setNews(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNews(List<NewsVO> mNewsList){
        this.mNewsList = mNewsList;
        //if data is added to adapter view, set this method
        notifyDataSetChanged();
    }
}
