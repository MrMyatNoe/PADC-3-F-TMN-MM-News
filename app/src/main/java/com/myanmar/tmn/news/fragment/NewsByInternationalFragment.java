package com.myanmar.tmn.news.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.ItemsInternationalNewsAdapter;
import com.myanmar.tmn.news.adapter.ItemsMainNewsAdapter;
import com.myanmar.tmn.news.adapter.OtherInternationalNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/7/2018.
 */

public class NewsByInternationalFragment extends Fragment {

    @BindView(R.id.rv_international_news)
    RecyclerView rvInternationalNews;

    private ItemsMainNewsAdapter itemsMainNewsAdapter;

    /*@BindView(R.id.rv_first_international_news)
    RecyclerView rvFirstInternationalNews;*/


    //private OtherInternationalNewsAdapter otherInternationalNewsAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_by_international,container,
                false);
        ButterKnife.bind(this,view);

        itemsMainNewsAdapter = new ItemsMainNewsAdapter();
        LinearLayoutManager itemsMainNewsManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rvInternationalNews.setLayoutManager(itemsMainNewsManager);
        rvInternationalNews.setAdapter(itemsMainNewsAdapter);

       /* otherInternationalNewsAdapter = new OtherInternationalNewsAdapter();
        LinearLayoutManager otherInternationalNewsManager = new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false);
        rvFirstInternationalNews.setLayoutManager(otherInternationalNewsManager);
        rvFirstInternationalNews.setAdapter(otherInternationalNewsAdapter);*/
        return view;
    }
}

