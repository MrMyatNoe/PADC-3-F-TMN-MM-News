package com.myanmar.tmn.news.delegates;

import com.myanmar.tmn.news.data.vo.NewsVO;

/**
 * Created by msi on 12/17/2017.
 */

public interface NewsActionDelegate {

    void onTapNewsItem(NewsVO tappedNews);

    void onTapCommentButton();

    void onTapSendToButton(NewsVO tappedNews);

    void onTapFavourite();

    void onTapLikeUsers(NewsVO tappedNews);

    void onTapCommentUsers(NewsVO tappedNews);

    void onTapSentToUsers(NewsVO tappedNews);
}
