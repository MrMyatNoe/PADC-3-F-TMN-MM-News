package com.myanmar.tmn.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.myanmar.tmn.news.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 2/4/2018.
 */

public class CommentDialog  extends Dialog{
    public CommentDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_comment);
        ButterKnife.bind(this,this);
        setCancelable(false);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view){
        dismiss();
    }
}
