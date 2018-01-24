package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.ImagesInNewsDetailsAdapter;
import com.myanmar.tmn.news.data.models.NewsModel;
import com.myanmar.tmn.news.data.vo.NewsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 12/9/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.tv_news_details)
    TextView newsDetails;

    @BindView(R.id.tv_publication_title)
    TextView publicationTitle;

    @BindView(R.id.iv_publication_logo)
    ImageView publicationLogo;

    @BindView(R.id.tv_posted_date)
    TextView postedDate;
    private ImagesInNewsDetailsAdapter imagesInNewsDetailsAdapter;

    public static Intent newDetailsIntent(Context context){
        Intent intent = new Intent(context,NewsDetailsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this, this);

        /*setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);*/

        imagesInNewsDetailsAdapter = new ImagesInNewsDetailsAdapter();
        vpNewsDetailsImages.setAdapter(imagesInNewsDetailsAdapter);

        //receiving data from main activity
        String newsId = getIntent().getStringExtra("News_id");
        NewsVO newsVO = NewsModel.getsObjectInstance().getNewsById(newsId);
        bindData(newsVO);
    }

    private void bindData(NewsVO vo) {
        newsDetails.setText(vo.getDetails());
        publicationTitle.setText(vo.getPublicationVO().getTitle());
        Glide.with(publicationLogo.getContext()).load(vo.getPublicationVO().getLogo())
                .into(publicationLogo);
        postedDate.setText(vo.getPostedDate());
    }
}