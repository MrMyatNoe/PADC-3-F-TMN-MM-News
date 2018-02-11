package com.myanmar.tmn.news.data.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.UserLogoutEvent;
import com.myanmar.tmn.news.network.NewsDataAgent;
import com.myanmar.tmn.news.network.RetrofitDataAgent;
import com.myanmar.tmn.news.utils.AppConstants;

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

    private Context context;

    private LoginUserModel(Context context) {
        newsDataAgent = RetrofitDataAgent.getsObjectInstance();
        EventBus.getDefault().register(this);
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP, Context.MODE_PRIVATE);
        int loginUserId = sharedPreferences.getInt(AppConstants.LOGIN_USER_ID, -1);
        if (loginUserId != -1) {
            //user has already logged in
            String name = sharedPreferences.getString(AppConstants.LOGIN_USER_NAME, null);

            loginUserVO = new LoginUserVO(loginUserId, name, "", "", "", "");
        }
    }

    public static LoginUserModel getsObjectInstance(Context context) {
        if (sObjectInstance == null) {
            sObjectInstance = new LoginUserModel(context);
        }
        return sObjectInstance;
    }

    public void login(Context context, String phone, String password) {
        newsDataAgent.LoginUser(context, phone, password);
    }

    public boolean isLogin() {
        return loginUserVO != null;
    }

    public void logOut() {
        loginUserVO = null;
        EventBus.getDefault().post(new UserLogoutEvent());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event) {
        loginUserVO = event.getLoginUser();

        //Save user data in SharedPreferences
        SharedPreferences sharedPreferences = event.getContext()
                .getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConstants.LOGIN_USER_ID, event.getLoginUser().getUserId());
        editor.putString(AppConstants.LOGIN_USER_NAME, event.getLoginUser().getName());
        editor.putString(AppConstants.LOGIN_USER_EMAIL, event.getLoginUser().getEmail());
        editor.putString(AppConstants.LOGIN_USER_PHONE, event.getLoginUser().getPhoneNo());
        editor.putString(AppConstants.LOGIN_USER_PROFILE, event.getLoginUser().getProfileUrl());
        editor.putString(AppConstants.LOGIN_USER_COVER, event.getLoginUser().getCoverUrl());
        editor.apply();
    }

    public LoginUserVO getLoginUser() {
        return loginUserVO;
    }
}
