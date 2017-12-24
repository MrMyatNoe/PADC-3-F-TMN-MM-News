package com.myanmar.tmn.news.data.vo;

/**
 * Created by msi on 12/17/2017.
 */

public class CommentsVO {

    private String commentId;
    private String comment;
    private String commentDate;

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
