package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

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
    private List<FavouriteVO> favouriteVOs;
    private List<CommentsVO> commentsVOs;
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
        return favouriteVOs;
    }

    public List<CommentsVO> getCommentsVOs() {
        return commentsVOs;
    }

    public List<SendTosVO> getSendTosVOs() {
        return sendTosVOs;
    }
}
