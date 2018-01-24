package com.myanmar.tmn.news.viewPod;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.delegates.LoginUserDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 1/14/2018.
 */

public class LoginUserViewPod extends RelativeLayout {

    @BindView(R.id.iv_login_user_image)
    ImageView loginUserImage;

    @BindView(R.id.tv_name)
    TextView userName;

    @BindView(R.id.tv_user_phone)
    TextView userPhone;

    private LoginUserDelegate loginUserDelegate;
    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void bindData(LoginUserVO loginUser){
        Glide.with(loginUserImage.getContext()).load(loginUser.getProfileUrl()).into(loginUserImage);
        userName.setText(loginUser.getName());
        userPhone.setText(loginUser.getPhoneNo());
    }

    @OnClick(R.id.btn_log_out)
    public void onTapLogOut(View view){
        loginUserDelegate.onTappedLogout();
    }

    public void setDelegate(LoginUserDelegate loginUserDelegate){
        this.loginUserDelegate = loginUserDelegate;
    }

}

