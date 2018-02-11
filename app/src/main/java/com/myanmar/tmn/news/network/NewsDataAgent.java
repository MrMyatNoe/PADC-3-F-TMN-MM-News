package com.myanmar.tmn.news.network;

import android.content.Context;

/**
 * Created by msi on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from internet
     */
    void loadNews();

    /**
     * login user
     * @param context
     * @param phone
     * @param password
     */
    void LoginUser(Context context, String phone, String password);
}
