package com.viznar.spendcube.android.readsms;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viznar.spendcube.android.R;

import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public class MessagesViewAdapter extends RecyclerView.Adapter<MessagesViewAdapter.MessagesViewHolder> {

    private List<MessageDataM> messageDataMList;
    private Activity activity;

    public MessagesViewAdapter(Activity activity, List<MessageDataM> messageDataMList){
        this.activity = activity;
        this.messageDataMList = messageDataMList;
    }

    public static class MessagesViewHolder extends RecyclerView.ViewHolder{
        TextView messageItemTextView;
        public MessagesViewHolder(View view){
            super(view);
            messageItemTextView = (TextView) view.findViewById(R.id.message_item_txtview);
        }
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_view,parent,false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
        holder.messageItemTextView.setText(messageDataMList.get(position).getMsgBody());
    }

    @Override
    public int getItemCount() {
        return messageDataMList.size();
    }
}
