package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.myanmar.tmn.news.MMNewsApp;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.NewsAdapter;
import com.myanmar.tmn.news.data.models.LoginUserModel;
import com.myanmar.tmn.news.data.models.NewsModel;
import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.data.vo.NewsVO;
import com.myanmar.tmn.news.delegates.BeforeLoginDelegate;
import com.myanmar.tmn.news.delegates.LoginUserDelegate;
import com.myanmar.tmn.news.delegates.NewsActionDelegate;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.LoadedNewsEvent;
import com.myanmar.tmn.news.viewPod.AccountControlViewPod;
import com.myanmar.tmn.news.viewPod.BeforeLoginUserViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NewsActionDelegate, BeforeLoginDelegate,LoginUserDelegate {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    private NewsAdapter newsAdapter;

   // private BeforeLoginUserViewPod beforeLoginUserViewPod;

    private AccountControlViewPod accountControlViewPod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate butterKnife
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.allNews);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        newsAdapter = new NewsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        rvNews.setLayoutManager(linearLayoutManager);

       /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),
               2);
       rvNews.setLayoutManager(gridLayoutManager);*/
        rvNews.setAdapter(newsAdapter);


        //for navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_news_by_category) {
                    // item.setChecked(true);
                    //Intent intent = NewsByCategoryActivity.newIntent(getApplicationContext());
                    startActivity(NewsByCategoryActivity.newIntent(getApplicationContext()));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

        //to construct view pod object
        //beforeLoginUserViewPod = (BeforeLoginUserViewPod) navigationView.getHeaderView(0);
        //beforeLoginUserViewPod.setBeforeLoginDelegate(this);
        accountControlViewPod = (AccountControlViewPod) navigationView.getHeaderView(0);
        accountControlViewPod.setDelegate((BeforeLoginDelegate) this);
        accountControlViewPod.setDelegate((LoginUserDelegate) this);

        NewsModel.getsObjectInstance().loadNews();
    }

    //if u r not state in onDestroy state, define in this place
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    //if u r not listen, define it
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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

        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        Snackbar.make(view, "Replace with your own action - Butterknife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        //adding data to another data
        intent.putExtra("News_id", tappedNews.getNewsId());
        startActivity(intent);
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

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    /*@OnClick(R.id.rv_news)
    public void onClickCard(View view){
        Intent intent = NewsDetailsActivity.newDetailsIntent(getApplicationContext());
        startActivity(intent);
    }*/

    //load news from network
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event) {
        Log.d(MMNewsApp.LOG_CAT, "onNewsLoaded " + event.getNewsList().size());
        newsAdapter.setNews(event.getNewsList());
    }

    @Override
    public void onTapToLogin() {
        Intent intent = AccountControlActivity.newIntentLogin(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapToRegister() {
        Intent intent = AccountControlActivity.newIntentRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTappedLogout() {
        LoginUserModel.getsObjectInstance().logOut();
    }
}
