package com.myanmar.tmn.news.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 2/3/2018.
 */

public class UserProfileActivity extends BaseActivity {

    @BindView(R.id.img_profile)
    ImageView imgProfile;

    @BindView(R.id.iv_bg)
    ImageView ivBg;

    public static Intent userProfileIntent(Context context) {
        return new Intent(context, UserProfileActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.img_profile)
    public void onTapEditProfileImage(View view) {
        /*Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();*/
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }

    //receive data to check request code and return call back
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 234 ){
           Uri uri = data.getData();
           Glide.with(getApplicationContext()).load(uri).into(imgProfile);
        } else if(requestCode == 345){
            //for thumbnails
            Bundle extras = data.getExtras();
            Bitmap takenPic = (Bitmap) extras.get("data");
            ivBg.setImageBitmap(takenPic);
        }
    }

    @OnClick(R.id.iv_edit)
    public void onTapEditCoverImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,345);
    }
}
