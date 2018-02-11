package com.myanmar.tmn.news.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.FavouriteVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 2/4/2018.
 */

public class FavouriteUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_profile)
    ImageView userProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_time_stamp)
    TextView favouriteTimeStamp;

    public FavouriteUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(FavouriteVO favouriteVO) {
        Glide.with(userProfile.getContext()).load(favouriteVO.getActedUserVO().getProfileImage())
                .into(userProfile);
        tvName.setText(favouriteVO.getActedUserVO().getUserName());
        String originalTimeFormat = favouriteVO.getFavouriteDate();


        //creating data format(SUN Dec 03 03:22:11 +GMT 2017)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy");
        try {
            Date date = simpleDateFormat.parse(originalTimeFormat);
            //to show in data layer
            SimpleDateFormat sdfPresentableFormat = new SimpleDateFormat("hh:mm a',' MMM dd yyyy");
            String presentableTimeFormat = sdfPresentableFormat.format(date);
            favouriteTimeStamp.setText(presentableTimeFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
