package com.myanmar.tmn.news.event;

import com.myanmar.tmn.news.data.vo.NewsVO;

import java.util.List;

/**
 * Created by msi on 12/24/2017.
 */

public class LoadedNewsEvent {

    private List<NewsVO> newsList;

    public LoadedNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
