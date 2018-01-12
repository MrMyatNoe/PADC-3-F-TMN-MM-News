package com.myanmar.tmn.news.fragment;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.MMNewsApp;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.NewsAdapter;
import com.myanmar.tmn.news.data.models.NewsModel;
import com.myanmar.tmn.news.delegates.NewsActionDelegate;
import com.myanmar.tmn.news.event.LoadedNewsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/7/2018.
 */

public class NewsByCategoryFragment extends Fragment implements NewsActionDelegate{


    @BindView(R.id.rv_news)
    RecyclerView rvNewsByCategory;

    NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_by_category,container,false);
        ButterKnife.bind(this,view);

        LinearLayoutManager newsLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,true);
        rvNewsByCategory.setLayoutManager(newsLayoutManager);

        newsAdapter = new NewsAdapter(this);
        rvNewsByCategory.setAdapter(newsAdapter);

        NewsModel.getsObjectInstance().loadNews();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapNewsItem() {

    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendToButton() {

    }

    @Override
    public void onTapFavourite() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event){
        Log.d(MMNewsApp.LOG_CAT,"onNewsLoaded " + event.getNewsList().size());
        newsAdapter.setNews(event.getNewsList());
    }
}
