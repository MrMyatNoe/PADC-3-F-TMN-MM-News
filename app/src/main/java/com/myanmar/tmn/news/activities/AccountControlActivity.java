package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.myanmar.tmn.news.MMNewsApp;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.LoginUserVO;
import com.myanmar.tmn.news.delegates.LoginScreenDelegate;
import com.myanmar.tmn.news.delegates.LoginUserDelegate;
import com.myanmar.tmn.news.fragment.LoginFragment;
import com.myanmar.tmn.news.fragment.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 1/20/2018.
 */

public class AccountControlActivity extends BaseActivity implements LoginScreenDelegate, GoogleApiClient.OnConnectionFailedListener {

    private static final String IE_SCREEN_TYPE = "IE_SCREEN_TYPE";
    private static final int SCREEN_LOGIN = 1;
    private static final int SCREEN_REGISTER = 2;

    //create google api client
    private GoogleApiClient googleApiClient;


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
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                    R.anim.pop_enter, R.anim.pop_exit).replace(R.id.fl_container,
                    new LoginFragment()).commit();
        } else if (screenType == SCREEN_REGISTER) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                    R.anim.pop_enter, R.anim.pop_exit).replace(R.id.fl_container,
                    new RegisterFragment()).commit();
        }
        //to show when an app is launched firstly
        setUpGoogleApiClient();
    }

    /**
     * if your push back buttton, enter is pop-enter, exit is pop-exit
     */
    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                R.anim.pop_enter, R.anim.pop_exit).addToBackStack("ToRegister").replace(R.id.fl_container,
                new RegisterFragment()).commit();
    }

    @Override
    public void onTapToLoginGoogle() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, 100);
    }

    private void setUpGoogleApiClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("783938162884-dc7bv1btv4kgp0m9iuhqt2tf0fpb8i4s.apps.googleusercontent.com")
                .requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign-In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                Toast.makeText(getApplicationContext(), "Google Sign-In success : "
                        + account.getDisplayName(), Toast.LENGTH_SHORT).show();
            } else {
                // Google Sign-In failed
                Log.e(MMNewsApp.LOG_CAT, "Google Sign-In failed.");
                Toast.makeText(getApplicationContext(), "Google Sign-In failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
