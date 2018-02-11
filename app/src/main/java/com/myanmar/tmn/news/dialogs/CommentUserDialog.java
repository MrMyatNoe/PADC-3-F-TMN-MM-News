package com.myanmar.tmn.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.CommentAdapter;
import com.myanmar.tmn.news.adapter.FavouriteAdapter;
import com.myanmar.tmn.news.data.vo.CommentsVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 2/8/2018.
 */

public class CommentUserDialog extends Dialog {

    @BindView(R.id.rv_comment_users)
    RecyclerView rvCommentUser;

    private CommentAdapter commentAdapter;

    public CommentUserDialog(@NonNull Context context, List<CommentsVO> commentsList) {
        super(context);
        setContentView(R.layout.dialog_comment_user);

        ButterKnife.bind(this,this);

        rvCommentUser.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        commentAdapter = new CommentAdapter();
        rvCommentUser.setAdapter(commentAdapter);
        commentAdapter.setData(commentsList);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view){
        dismiss();
    }
}
