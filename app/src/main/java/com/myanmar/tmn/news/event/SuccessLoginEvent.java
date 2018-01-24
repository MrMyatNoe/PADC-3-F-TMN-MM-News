package com.myanmar.tmn.news.event;

import com.myanmar.tmn.news.data.vo.LoginUserVO;

/**
 * Created by msi on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LoginUserVO loginUser;

    public SuccessLoginEvent(LoginUserVO loginUser) {
        this.loginUser = loginUser;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}
