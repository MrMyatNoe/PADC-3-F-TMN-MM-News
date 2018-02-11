package com.myanmar.tmn.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.adapter.SendToAdapter;
import com.myanmar.tmn.news.data.vo.SendTosVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by msi on 2/9/2018.
 */

public class SendToUserDialog extends Dialog {

    @BindView(R.id.rv_send_to)
    RecyclerView rvSendTo;

    private SendToAdapter sendToAdapter;

    public SendToUserDialog(@NonNull Context context, List<SendTosVO> sendToList) {
        super(context);
        setContentView(R.layout.dialog_send_to_user);
        ButterKnife.bind(this,this);

        rvSendTo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvSendTo.setAdapter(new SendToAdapter());
        sendToAdapter.setData(sendToList);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view){
        dismiss();
    }
}
