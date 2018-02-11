package com.myanmar.tmn.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.FavouriteAdapter;
import com.myanmar.tmn.news.data.vo.FavouriteVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 2/4/2018.
 */

public class LikeUserDialog extends Dialog {

    @BindView(R.id.rv_like_users)
    RecyclerView rvLikeUsers;

    private FavouriteAdapter favouriteAdapter;

    public LikeUserDialog(@NonNull Context context, List<FavouriteVO> favouriteList) {
        super(context);
        setContentView(R.layout.dialog_like_user);

        ButterKnife.bind(this,this);
        setCancelable(true);

        rvLikeUsers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        favouriteAdapter = new FavouriteAdapter();
        rvLikeUsers.setAdapter(favouriteAdapter);
        favouriteAdapter.setData(favouriteList);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view){
        dismiss();
    }
}
