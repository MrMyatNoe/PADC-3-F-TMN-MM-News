package com.myanmar.tmn.news.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.SendTosVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 2/9/2018.
 */

public class SendToUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_send_user)
    TextView tvSendUser;

    @BindView(R.id.tv_received_user)
    TextView tvReceivedUser;

    @BindView(R.id.user_send_profile)
    ImageView userSendProfile;

    @BindView(R.id.user_received_profile)
    ImageView userReceivedProfile;

    public SendToUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(SendTosVO sendTosVO){
        tvSendUser.setText(sendTosVO.getActedUserVO().getUserName());
        tvReceivedUser.setText(sendTosVO.getReceivedUserVO().getUserName());
        Glide.with(userSendProfile.getContext()).load(sendTosVO.getActedUserVO().getProfileImage())
                .into(userSendProfile);
        Glide.with(userReceivedProfile.getContext()).load(sendTosVO.getReceivedUserVO().getProfileImage())
                .into(userReceivedProfile);
    }
}
