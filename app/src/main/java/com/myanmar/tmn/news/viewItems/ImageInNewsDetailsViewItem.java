package com.myanmar.tmn.news.viewItems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 12/10/2017.
 */

public class ImageInNewsDetailsViewItem extends FrameLayout{

    @BindView(R.id.iv_news_details_image)
    ImageView imgNewsDetailsImage;

    public ImageInNewsDetailsViewItem(@NonNull Context context) {
        super(context);
    }

    public ImageInNewsDetailsViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageInNewsDetailsViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //override to bind using butterKnife
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    //to receive data
    public void setData(String imgUrl){
        Glide.with(imgNewsDetailsImage.getContext()).load(imgUrl)
                .into(imgNewsDetailsImage);
    }
}
