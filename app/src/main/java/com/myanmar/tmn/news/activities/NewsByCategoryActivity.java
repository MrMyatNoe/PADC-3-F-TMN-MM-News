package com.myanmar.tmn.news.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.NewsByCategoryAdapter;
import com.myanmar.tmn.news.fragment.NewsByCategoryFragment;
import com.myanmar.tmn.news.fragment.NewsByInternationalFragment;
import com.myanmar.tmn.news.fragment.NewsBySportsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/7/2018.
 */

public class NewsByCategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_new_by_category)
    ViewPager vpNewsByCategory;

    @BindView(R.id.tl_news_by_category)
    TabLayout tabLayoutNewsCategory;

    private NewsByCategoryAdapter newsByCategoryAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_category);
        ButterKnife.bind(this,this);


        //show app name by default
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.titleNewsByCategory);

            //set home button
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        newsByCategoryAdapter = new NewsByCategoryAdapter(getSupportFragmentManager());

        newsByCategoryAdapter.addTab("Local News",new NewsByCategoryFragment());
        newsByCategoryAdapter.addTab("International News",new NewsByInternationalFragment());
        newsByCategoryAdapter.addTab("Sports News",new NewsBySportsFragment());

        vpNewsByCategory.setAdapter(newsByCategoryAdapter);
        tabLayoutNewsCategory.setupWithViewPager(vpNewsByCategory);
        vpNewsByCategory.setOffscreenPageLimit(newsByCategoryAdapter.getCount());
    }
}
