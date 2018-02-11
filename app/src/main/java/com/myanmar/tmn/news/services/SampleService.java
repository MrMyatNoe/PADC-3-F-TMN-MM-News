package com.myanmar.tmn.news.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.myanmar.tmn.news.MMNewsApp;

/**
 * Created by msi on 2/10/2018.
 */

public class SampleService extends IntentService {


    private static final String IE_TIMESTAMP = "timestamp";

    //for debug
    public SampleService() {
        super("SampleService");
    }

    public static Intent sendIntent(Context context, String timestamp) {
        Intent intent = new Intent(context, SampleService.class);
        intent.putExtra("timestamp", timestamp);
        return intent;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String timeStamp = " ";
        if (intent != null) {
            timeStamp = intent.getStringExtra(IE_TIMESTAMP);
        }
        Log.d(MMNewsApp.LOG_CAT, "Sample Service : onHandleIntent" + timeStamp);
    }
}
