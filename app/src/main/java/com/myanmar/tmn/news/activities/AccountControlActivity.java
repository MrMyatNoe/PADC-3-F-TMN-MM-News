package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.fragment.LoginFragment;
import com.myanmar.tmn.news.fragment.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/20/2018.
 */

public class AccountControlActivity extends AppCompatActivity {

    private static final String IE_SCREEN_TYPE = "IE_SCREEN_TYPE";
    private static final int SCREEN_LOGIN = 1;
    private static final int SCREEN_REGISTER = 2;

    public static Intent newIntentLogin(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE, SCREEN_LOGIN);
        return intent;
    }

    public static Intent newIntentRegister(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE, SCREEN_REGISTER);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        ButterKnife.bind(this, this);

        //choosing screen type
        int screenType = getIntent().getIntExtra(IE_SCREEN_TYPE, -1);
        if (screenType == SCREEN_LOGIN) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new LoginFragment()).commit();
        } else if (screenType == SCREEN_REGISTER) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    new RegisterFragment()).commit();
        }
    }

}
