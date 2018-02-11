package com.myanmar.tmn.news.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import com.myanmar.tmn.news.dialogs.CommentDialog;
import com.myanmar.tmn.news.dialogs.CommentUserDialog;
import com.myanmar.tmn.news.dialogs.LikeUserDialog;
import com.myanmar.tmn.news.dialogs.SendToUserDialog;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.LoadedNewsEvent;
import com.myanmar.tmn.news.services.SampleService;
import com.myanmar.tmn.news.viewPod.AccountControlViewPod;
import com.myanmar.tmn.news.viewPod.BeforeLoginUserViewPod;
import com.myanmar.tmn.news.viewPod.EmptyViewPod;

import net.aungpyaephyo.mmtextview.components.MMProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NewsActionDelegate, BeforeLoginDelegate, LoginUserDelegate {

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

    @BindView(R.id.empty_layout)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter newsAdapter;

    // private BeforeLoginUserViewPod beforeLoginUserViewPod;

    private AccountControlViewPod accountControlViewPod;

    MMProgressDialog mmProgressDialog;

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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //load data
                NewsModel.getsObjectInstance().loadNews();
            }
        });

        //to show it working
        swipeRefreshLayout.setRefreshing(true);
        NewsModel.getsObjectInstance().loadNews();

        //to show progress dialog
        mmProgressDialog = new MMProgressDialog(this);
        mmProgressDialog.setMessage("Please wait while data is loading!");
        mmProgressDialog.show();
    }

    //if u don't want to state in onDestroy state, define in this place
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

    //u must override to check permission result for dynamic
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            //request call phone permission
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String numberToCall = "+95979599035";
                callToNumber(numberToCall);
            }
        }
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {

        //Calling ph no
       /* String numberToCall = "+95972170213";
        callToNumber(numberToCall);*/


       /* showConfirmDialog();*/

        /*Snackbar.make(view, "Replace with your own action - Butterknife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

       startServiceComponent();
    }

    private void callToNumber(String numberToCall) {
        Uri numberToCallUri = Uri.parse("tel" + numberToCall);
        Intent intentToCall = new Intent(Intent.ACTION_CALL, numberToCallUri);

        //check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
            return;
        }
        startActivity(intentToCall);
    }

    //adding lateral
    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setCancelable(false)
                .setMessage(getResources().getString(R.string.message_exit,
                        LoginUserModel.getsObjectInstance(getApplicationContext()).getLoginUser().getName()))
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(rvNews, "Ok, Got it", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Ok, do it", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //execute by name i.e explicit
    private void startServiceComponent() {
        Intent intent = SampleService.sendIntent(getApplicationContext(),new Date().toString());
        startService(intent);
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
        CommentDialog commentDialog = new CommentDialog(this);
        commentDialog.show();
    }

    @Override
    public void onTapSendToButton(NewsVO tappedNews) {
        //for only sharing with myme type
        Intent shareIntent = ShareCompat.IntentBuilder.from(this).setType("text/plain")
                .setType(tappedNews.getBrief()).getIntent();

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        } else {
            Snackbar.make(rvNews, "No app to handle Share action", Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    @Override
    public void onTapFavourite() {

    }

    @Override
    public void onTapLikeUsers(NewsVO tappedNews) {
        LikeUserDialog userDialog = new LikeUserDialog(this, tappedNews.getFavouriteVOs());
        userDialog.show();
    }

    @Override
    public void onTapCommentUsers(NewsVO tappedNews) {
        CommentUserDialog commentUserDialog = new CommentUserDialog(this, tappedNews.getCommentsVOs());
        commentUserDialog.show();
    }

    @Override
    public void onTapSentToUsers(NewsVO tappedNews) {
        SendToUserDialog sendToUserDialog = new SendToUserDialog(this, tappedNews.getSendTosVOs());
        sendToUserDialog.show();
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
        //if u don't want to see loading
        swipeRefreshLayout.setRefreshing(false);
        mmProgressDialog.dismiss();
        if (!event.getNewsList().isEmpty()) {
            newsAdapter.setNews(event.getNewsList());
            emptyViewPod.setVisibility(View.GONE);
        } /*else {
            emptyViewPod.setVisibility(View.VISIBLE);
        }*/
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
        LoginUserModel.getsObjectInstance(getApplicationContext()).logOut();
        finish();
    }

    @Override
    public void onTapLoginUser() {
        startActivity(UserProfileActivity.userProfileIntent(getApplicationContext()));
    }
}
