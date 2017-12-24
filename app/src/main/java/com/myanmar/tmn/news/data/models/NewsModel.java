package com.myanmar.tmn.news.data.models;

import com.myanmar.tmn.news.network.HttpUrlConnectionDataAgent;
import com.myanmar.tmn.news.network.NewsDataAgent;

/**
 * Created by msi on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjectInstance;
    private NewsDataAgent mDataAgent;

    private NewsModel() {
        mDataAgent = HttpUrlConnectionDataAgent.getsObjectInstance();
    }

    public static NewsModel getsObjectInstance() {
        if (sObjectInstance == null){
            sObjectInstance = new NewsModel();
        }
        return sObjectInstance;
    }

    /**
     * load news from network calls
    */
    public void loadNews(){
        mDataAgent.loadNews();
    }
}
