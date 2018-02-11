package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msi on 12/17/2017.
 */

public class SendTosVO {

    @SerializedName("send-to-id")
    private String sendToId;
    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUserVO;
    @SerializedName("received-user")
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
