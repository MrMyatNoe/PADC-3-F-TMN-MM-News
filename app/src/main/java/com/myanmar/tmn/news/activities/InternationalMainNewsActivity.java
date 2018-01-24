package com.myanmar.tmn.news.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.ItemsInternationalNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/19/2018.
 */

public class InternationalMainNewsActivity extends AppCompatActivity {

    @BindView(R.id.rv_international_news_topic)
    RecyclerView rvInternationalNewsTopic;

    private ItemsInternationalNewsAdapter itemsInternationalNewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_news);
        ButterKnife.bind(this);

        itemsInternationalNewsAdapter = new ItemsInternationalNewsAdapter();
        LinearLayoutManager internationalNewsManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        rvInternationalNewsTopic.setLayoutManager(internationalNewsManager);
        rvInternationalNewsTopic.setAdapter(itemsInternationalNewsAdapter);
    }
}
