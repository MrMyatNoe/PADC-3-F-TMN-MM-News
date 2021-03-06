package com.myanmar.tmn.news.network;

import android.content.Context;

import com.google.gson.Gson;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.LoadedNewsEvent;
import com.myanmar.tmn.news.network.responses.GetLoginResponse;
import com.myanmar.tmn.news.network.responses.GetNewsResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by msi on 1/6/2018.
 */

public class RetrofitDataAgent implements NewsDataAgent {

    /**
     * singleton for retrofit
     */
    private static RetrofitDataAgent sObjectInstance;

    private NewsApi newsApi;

    /**
     * singleton for retrofit
     */
    private RetrofitDataAgent() {
        OkHttpClient httpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder() //2
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

        newsApi = retrofit.create(NewsApi.class); //3
    }

    /**
     * singleton for retrofit
     *
     * @return
     */
    public static RetrofitDataAgent getsObjectInstance() {
        if (sObjectInstance == null) {
            sObjectInstance = new RetrofitDataAgent();
        }
        return sObjectInstance;
    }

    @Override
    public void loadNews() {

        //create object from NewsApi
        Call<GetNewsResponse> getNewsResponseCall = newsApi.getNews(1,
                "b002c7e1a528b7cb460933fc2875e916");

        //call in background thread
        getNewsResponseCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse getNewsResponse = response.body();
                if (getNewsResponse != null) {
                    LoadedNewsEvent loadedNewsEvent = new LoadedNewsEvent(getNewsResponse.
                            getMmNews());
                    EventBus.getDefault().post(loadedNewsEvent);
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void LoginUser(final Context context, String phone, String password) {
        Call<GetLoginResponse> getLoginResponseCall = newsApi.getLogin(phone,
                password);

        getLoginResponseCall.enqueue(new Callback<GetLoginResponse>() {
            @Override
            public void onResponse(Call<GetLoginResponse> call, Response<GetLoginResponse> response) {
                GetLoginResponse getLoginResponse = response.body();
                if (getLoginResponse != null) {
                    EventBus.getDefault().post(new SuccessLoginEvent(getLoginResponse.getLoginUser(),context));
                }
            }

            @Override
            public void onFailure(Call<GetLoginResponse> call, Throwable t) {

            }
        });
    }
}
