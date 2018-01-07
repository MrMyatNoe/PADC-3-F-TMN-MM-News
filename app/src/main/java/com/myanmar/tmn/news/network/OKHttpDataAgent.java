package com.myanmar.tmn.news.network;

import android.os.AsyncTask;
import android.util.EventLog;
import android.util.Log;

import com.google.gson.Gson;
import com.myanmar.tmn.news.MMNewsApp;
import com.myanmar.tmn.news.event.LoadedNewsEvent;
import com.myanmar.tmn.news.network.responses.GetNewsResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by msi on 1/6/2018.
 */

public class OKHttpDataAgent implements NewsDataAgent {

    private static OKHttpDataAgent sObjectInstance;

    private OKHttpDataAgent() {
    }

    public static OKHttpDataAgent getsObjectInstance() {
        if (sObjectInstance == null) {
            sObjectInstance = new OKHttpDataAgent();
        }
        return sObjectInstance;
    }

    @Override
    public void loadNews() {
        new LoadNewsTask().execute("http://padcmyanmar.com/padc-3/mm-news/apis/v1/getMMNews.php");
    }

    /**
     * <params, progress, return (JSON)></params,>
     */
    private static class LoadNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];

            OkHttpClient httpClient = new OkHttpClient.Builder() //1.
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            RequestBody formBody = new FormBody.Builder() //2.
                    .add("access_token", "b002c7e1a528b7cb460933fc2875e916")
                    .add("page", "1")
                    .build();

            Request request = new Request.Builder() //3
                    .url(url)
                    .post(formBody)
                    .build();

            String responseString = null;
            try {
                Response response = httpClient.newCall(request).execute(); //4.
                if (response.isSuccessful() && response.body() != null) {
                    responseString = response.body().string();
                }
            } catch (IOException e) {
                Log.e(MMNewsApp.LOG_CAT, e.getMessage());
            }
            return responseString;
        }

        //callback to show data in UI thread
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            //build gson object
            Gson gson = new Gson();

            //return from api type
            GetNewsResponse getNewsResponse = gson.fromJson(response, GetNewsResponse.class);
            Log.d(MMNewsApp.LOG_CAT," size " + getNewsResponse.getMmNews().size());

            //event to broadcast
          //  EventBus.getDefault().post(new LoadedNewsEvent(getNewsResponse.getMmNews()));
            LoadedNewsEvent loadedNewsEvent = new LoadedNewsEvent(getNewsResponse.getMmNews());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(loadedNewsEvent);
        }
    }

}
