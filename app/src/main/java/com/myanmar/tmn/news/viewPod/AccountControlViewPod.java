package com.myanmar.tmn.news.viewPod;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.models.LoginUserModel;
import com.myanmar.tmn.news.delegates.BeforeLoginDelegate;
import com.myanmar.tmn.news.delegates.LoginUserDelegate;
import com.myanmar.tmn.news.event.SuccessLoginEvent;
import com.myanmar.tmn.news.event.UserLogoutEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 1/21/2018.
 */

public class AccountControlViewPod extends FrameLayout {

    @BindView(R.id.vp_before_login)
    BeforeLoginUserViewPod vpBeforeLogin;

    @BindView(R.id.vp_login_user)
    LoginUserViewPod vpLoginUser;

    LoginUserDelegate mLoginUserDelegate;

    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //Bind butterKnife and create business logic
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);

        refreshUserSession();
        EventBus.getDefault().register(this);
    }

    public void setDelegate(BeforeLoginDelegate beforeLoginDelegate) {
        vpBeforeLogin.setBeforeLoginDelegate(beforeLoginDelegate);
    }

    public void setDelegate(LoginUserDelegate loginDelegate){
            vpLoginUser.setDelegate(loginDelegate);
            mLoginUserDelegate = loginDelegate;
    }

    public void refreshUserSession() {
        if (LoginUserModel.getsObjectInstance(getContext()).isLogin()) {
            vpBeforeLogin.setVisibility(View.GONE);
            vpLoginUser.setVisibility(View.VISIBLE);
        } else {
            vpBeforeLogin.setVisibility(View.VISIBLE);
            vpLoginUser.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.vp_login_user)
    public void onTapLoginUser(View view){
        mLoginUserDelegate.onTapLoginUser();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event) {
        vpBeforeLogin.setVisibility(View.GONE);
        vpLoginUser.setVisibility(View.VISIBLE);

        //bind data from login user
        vpLoginUser.bindData(event.getLoginUser());
    }

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onLogoutUser(UserLogoutEvent event){
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);
    }
}
