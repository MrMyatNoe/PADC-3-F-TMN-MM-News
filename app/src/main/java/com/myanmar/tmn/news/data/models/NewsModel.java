package com.myanmar.tmn.news.data.models;

import com.myanmar.tmn.news.data.vo.NewsVO;
import com.myanmar.tmn.news.event.LoadedNewsEvent;
import com.myanmar.tmn.news.network.HttpUrlConnectionDataAgent;
import com.myanmar.tmn.news.network.NewsDataAgent;
import com.myanmar.tmn.news.network.OKHttpDataAgent;
import com.myanmar.tmn.news.network.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by msi on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjectInstance;

    //dependency for injection
    private NewsDataAgent mDataAgent;

    private Map<String,NewsVO> mNews;

    /**
     * load data agent call
     */
    private NewsModel() {
        //mDataAgent = HttpUrlConnectionDataAgent.getsObjectInstance();
        //mDataAgent = OKHttpDataAgent.getsObjectInstance();
        mDataAgent = RetrofitDataAgent.getsObjectInstance();
        mNews = new HashMap<>();
        EventBus.getDefault().register(this);
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

    //call from network
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsLoaded(LoadedNewsEvent event){
        for (NewsVO vo : event.getNewsList()){
            mNews.put(vo.getNewsId(),vo);
        }
    }

    /**
     * getNews object by id
     * @param newsId
     * @return
     */
    public NewsVO getNewsById(String newsId){
       return mNews.get(newsId);
    }
}
