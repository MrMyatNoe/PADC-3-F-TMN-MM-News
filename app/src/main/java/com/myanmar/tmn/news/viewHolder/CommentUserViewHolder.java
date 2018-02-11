package com.myanmar.tmn.news.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.CommentsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by msi on 2/9/2018.
 */

public class CommentUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_profile)
    ImageView userProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_comment)
    TextView tvComment;

    @BindView(R.id.tv_time_stamp)
    TextView favouriteTimeStamp;

    public CommentUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(CommentsVO commentsVO) {
        Glide.with(userProfile.getContext()).load(commentsVO.getActedUserVO().getProfileImage())
                .into(userProfile);

        tvName.setText(commentsVO.getActedUserVO().getUserName());
        tvComment.setText(commentsVO.getComment());

        favouriteTimeStamp.setText(commentsVO.getCommentDate());
    }
}
