package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msi on 12/17/2017.
 */

public class CommentsVO {

    @SerializedName("comment-id")
    private String commentId;
    @SerializedName("comment")
    private String comment;
    @SerializedName("comment-date")
    private String commentDate;
    @SerializedName("acted-user")
    private ActedUserVO actedUserVO;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUserVO() {
        return actedUserVO;
    }
}
