package com.myanmar.tmn.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.models.LoginUserModel;

import com.myanmar.tmn.news.delegates.LoginScreenDelegate;
import com.myanmar.tmn.news.event.SuccessLoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 1/20/2018.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.ed_mail_phone)
    EditText edPhone;

    @BindView(R.id.ed_password)
    EditText edPassword;

    private LoginScreenDelegate loginScreenDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loginScreenDelegate = (LoginScreenDelegate) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_login_layout,
                container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View view) {
        String emailOrPhone = edPhone.getText().toString();
        String password = edPassword.getText().toString();
        LoginUserModel.getsObjectInstance(getContext()).login(getContext(), emailOrPhone, password);
    }

    @OnClick(R.id.btn_register)
    public void onClickRegister(View view) {
        loginScreenDelegate.onTapToRegister();
    }

    @OnClick(R.id.btn_login_google)
    public void onTapLoginGoogle(View view) {
        loginScreenDelegate.onTapToLoginGoogle();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
