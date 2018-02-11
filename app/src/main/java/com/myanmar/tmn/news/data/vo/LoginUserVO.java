package com.myanmar.tmn.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msi on 1/21/2018.
 */

public class LoginUserVO {

    private int userId;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNo")
    private String phoneNo;

    @SerializedName("profileUrl")
    private String profileUrl;

    @SerializedName("coverUrl")
    private String coverUrl;

    public LoginUserVO() {

    }

    public LoginUserVO(int userId, String name, String email, String phoneNo, String profileUrl, String coverUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.profileUrl = profileUrl;
        this.coverUrl = coverUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}
