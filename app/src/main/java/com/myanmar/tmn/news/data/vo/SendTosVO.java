package com.myanmar.tmn.news.data.vo;

/**
 * Created by msi on 12/17/2017.
 */

public class SendTosVO {

    private String sendToId;
    private String sentDate;

    private ActedUserVO actedUserVO;
    private ActedUserVO receivedUserVO;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getActedUserVO() {
        return actedUserVO;
    }

    public ActedUserVO getReceivedUserVO() {
        return receivedUserVO;
    }
}
