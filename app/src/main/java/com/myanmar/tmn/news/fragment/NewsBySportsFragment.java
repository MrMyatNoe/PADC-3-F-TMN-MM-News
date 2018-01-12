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
import com.myanmar.tmn.news.adapter.SportsNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/7/2018.
 */

public class NewsBySportsFragment extends Fragment {

    @BindView(R.id.rv_sports_news)
    RecyclerView rvSportNews;

    private SportsNewsAdapter sportsNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_by_sports,container,
                false);

        ButterKnife.bind(this,view);
        sportsNewsAdapter = new SportsNewsAdapter();
        LinearLayoutManager sportsManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,true);
        rvSportNews.setLayoutManager(sportsManager);
        rvSportNews.setAdapter(sportsNewsAdapter);
        return view;
    }
}
