package com.myanmar.tmn.news.network.responses;

import com.google.gson.annotations.SerializedName;
import com.myanmar.tmn.news.data.vo.LoginUserVO;

/**
 * Created by msi on 1/21/2018.
 */

public class GetLoginResponse {

    private int code;

    private String message;

    @SerializedName("login_user")
    private LoginUserVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}
