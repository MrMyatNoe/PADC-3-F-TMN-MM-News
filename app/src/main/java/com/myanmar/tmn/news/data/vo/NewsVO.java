package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 12/17/2017.
 */

public class NewsVO {

    @SerializedName("news-id")
    private String newsId;
    private String brief;
    private String details;

    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVO publicationVO;
    @SerializedName("favorites")
    private List<FavouriteVO> favouriteVOs;
    @SerializedName("comments")
    private List<CommentsVO> commentsVOs;
    @SerializedName("sent-tos")
    private List<SendTosVO> sendTosVOs;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublicationVO() {
        return publicationVO;
    }

    public List<FavouriteVO> getFavouriteVOs() {
        if ( favouriteVOs == null){
            favouriteVOs = new ArrayList<>();
        }
        return favouriteVOs;
    }

    public List<CommentsVO> getCommentsVOs() {
        if (commentsVOs == null){
            commentsVOs = new ArrayList<>();
        }
        return commentsVOs;
    }

    public List<SendTosVO> getSendTosVOs() {
        if (sendTosVOs == null){
            sendTosVOs = new ArrayList<>();
        }
        return sendTosVOs;
    }
}
