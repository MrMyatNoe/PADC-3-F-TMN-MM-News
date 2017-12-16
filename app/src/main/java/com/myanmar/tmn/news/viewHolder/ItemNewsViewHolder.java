package com.myanmar.tmn.news.viewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.myanmar.tmn.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 12/3/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    public ItemNewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.cv_news_items_root)
    public void onClick(View view){
        Toast.makeText(view.getContext(),"Hello",Toast.LENGTH_SHORT).show();
    }


}
