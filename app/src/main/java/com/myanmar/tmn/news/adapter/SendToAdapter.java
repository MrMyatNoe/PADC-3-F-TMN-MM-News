package com.myanmar.tmn.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.data.vo.SendTosVO;
import com.myanmar.tmn.news.viewHolder.SendToUserViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2/9/2018.
 */

public class SendToAdapter extends RecyclerView.Adapter<SendToUserViewHolder> {

    private List<SendTosVO> sendToList;

    public SendToAdapter() {
        sendToList = new ArrayList<>();
    }

    @Override
    public SendToUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SendToUserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_send_to,parent,false));
    }

    @Override
    public void onBindViewHolder(SendToUserViewHolder holder, int position) {
        holder.setData(sendToList.get(position));
    }

    @Override
    public int getItemCount() {
        return sendToList.size();
    }

    public void setData(List<SendTosVO> sendToList){
        this.sendToList = sendToList;
    }
}
