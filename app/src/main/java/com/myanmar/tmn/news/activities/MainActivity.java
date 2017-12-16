package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.NewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate butterKnife
        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);

        newsAdapter = new NewsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false);
        rvNews.setLayoutManager(linearLayoutManager);

       /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),
               2);
       rvNews.setLayoutManager(gridLayoutManager);*/
       rvNews.setAdapter(newsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view){
        Snackbar.make(view, "Replace with your own action - Butterknife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /*@OnClick(R.id.rv_news)
    public void onClickCard(View view){
        Intent intent = NewsDetailsActivity.newDetailsIntent(getApplicationContext());
        startActivity(intent);
    }*/
}
