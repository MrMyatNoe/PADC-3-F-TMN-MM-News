package com.myanmar.tmn.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.CommentsVO;
import com.myanmar.tmn.news.viewHolder.CommentUserViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2/9/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentUserViewHolder>{

    private List<CommentsVO> commentsList;

    public CommentAdapter() {
       commentsList = new ArrayList<>();
    }

    @Override
    public CommentUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments,parent,false));
    }

    @Override
    public void onBindViewHolder(CommentUserViewHolder holder, int position) {
        holder.setData(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public void setData(List<CommentsVO> commentsVO){
        commentsList = commentsVO;
    }
}
