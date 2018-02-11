package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msi on 12/17/2017.
 */

public class FavouriteVO {

    @SerializedName("favorite-id")
    private String favouriteId;

    @SerializedName("favorite-date")
    private String favouriteDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUserVO;

    public String getFavouriteId() {
        return favouriteId;
    }

    public String getFavouriteDate() {
        return favouriteDate;
    }

    public ActedUserVO getActedUserVO() {
        return actedUserVO;
    }
}
