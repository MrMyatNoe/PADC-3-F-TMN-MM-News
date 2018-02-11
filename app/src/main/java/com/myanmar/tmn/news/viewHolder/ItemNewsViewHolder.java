package com.myanmar.tmn.news.viewHolder;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.activities.MainActivity;
import com.myanmar.tmn.news.data.vo.NewsVO;
import com.myanmar.tmn.news.delegates.NewsActionDelegate;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 12/3/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_publication_title)
    TextView publicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView postedDate;

    @BindView(R.id.tv_news_brief)
    TextView newsBrief;

    @BindView(R.id.iv_publication_logo)
    ImageView publicationLogo;

    @BindView(R.id.img_news_bunny)
    ImageView newsBunny;

    @BindView(R.id.tv_likes)
    TextView tvLikes;

    @BindView(R.id.tv_comment)
    TextView tvComment;

    @BindView(R.id.tv_send_to)
    TextView tvSendTo;

    private NewsVO mNews;

    private NewsActionDelegate actionDelegate;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        actionDelegate = newsActionDelegate;
    }

    @OnClick(R.id.cv_news_items_root)
    public void onClick(View view){
       // Toast.makeText(view.getContext(),"Hello",Toast.LENGTH_SHORT).show();
        actionDelegate.onTapNewsItem(mNews);
    }

    @OnClick(R.id.fl_send_to)
    public void onTapSendTo(View view){
        actionDelegate.onTapSendToButton(mNews);
    }

    //add for one view
    public void setNews(NewsVO news) {
        mNews = news;
        publicationTitle.setText(news.getPublicationVO().getTitle());
        postedDate.setText(news.getPostedDate());
        newsBrief.setText(news.getBrief());

        Glide.with(publicationLogo.getContext()).load(news.getPublicationVO().getLogo())
                .into(publicationLogo);

        if (news.getImages() != null) {
            newsBunny.setVisibility(View.VISIBLE);
            Glide.with(newsBunny.getContext()).load(news.getImages().get(0)).into(newsBunny);

        } else {
            newsBunny.setVisibility(View.GONE);
        }
        tvLikes.setText(tvLikes.getContext().getResources().getString(R.string.format_like_users,news.getFavouriteVOs().size()));
        tvComment.setText(tvLikes.getContext().getResources().getString(R.string.format_comment_users,news.getCommentsVOs().size()));
        tvSendTo.setText(tvSendTo.getContext().getResources().getString(R.string.format_send_to_users,news.getSendTosVOs().size()));
    }

    @OnClick(R.id.tv_likes)
    public void onTappedLikeUser(View view){
        actionDelegate.onTapLikeUsers(mNews);
    }

    @OnClick(R.id.tv_comment)
    public void onTappedCommentUser(View view){
        actionDelegate.onTapCommentUsers(mNews);
    }

    @OnClick(R.id.tv_send_to)
    public void onTappedSendToUser(View view){
        actionDelegate.onTapSendToButton(mNews);
    }

    @OnClick(R.id.fl_comment)
    public void onTappedComment(View view){
        actionDelegate.onTapCommentButton();
    }

}

