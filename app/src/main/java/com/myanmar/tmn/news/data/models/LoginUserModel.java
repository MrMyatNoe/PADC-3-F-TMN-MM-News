package com.myanmar.tmn.news.data.models;

import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.UserLogoutEvent;
import com.myanmar.tmn.news.network.NewsDataAgent;
import com.myanmar.tmn.news.network.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/**
 * Created by msi on 1/20/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sObjectInstance;

    private NewsDataAgent newsDataAgent;

    private LoginUserVO loginUserVO;

    private LoginUserModel() {
        newsDataAgent = RetrofitDataAgent.getsObjectInstance();
        EventBus.getDefault().register(this);
    }

    public static LoginUserModel getsObjectInstance() {
        if (sObjectInstance == null){
            sObjectInstance = new LoginUserModel();
        }
        return sObjectInstance;
    }

    public void login(String phone, String password){
        newsDataAgent.LoginUser(phone,password);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        loginUserVO = event.getLoginUser();
    }

    public boolean isLogin(){
        return loginUserVO != null;
    }

    public void logOut(){
        loginUserVO = null;
        EventBus.getDefault().post(new UserLogoutEvent());
    }
}
