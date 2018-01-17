package com.viznar.spendcube.android.readsms;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viznar.spendcube.android.R;
import com.viznar.spendcube.android.data.db.MessageEntity;

import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public class MessagesViewAdapter extends RecyclerView.Adapter<MessagesViewAdapter.MessagesViewHolder> {

    private List<MessageEntity> messageDataMList;

    private MessageItemActionListener messageItemActionListener;

    public MessagesViewAdapter( List<MessageEntity> messageDataMList, MessageItemActionListener messageItemActionListener){
        this.messageDataMList = messageDataMList;
        this.messageItemActionListener = messageItemActionListener;
    }

    public static class MessagesViewHolder extends RecyclerView.ViewHolder{
        TextView messageItemTextView;
        TextView messageSectionTextView;
        TextView messageSenderTextView;
        public MessagesViewHolder(View view){
            super(view);
            messageItemTextView = (TextView) view.findViewById(R.id.message_item_txtview);
            messageSectionTextView = (TextView) view.findViewById(R.id.sectionview_txt);
            messageSenderTextView = (TextView) view.findViewById(R.id.message_header_item_txtview);
        }
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_view,parent,false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {

        final MessageEntity messageEntity = messageDataMList.get(position);
        holder.messageItemTextView.setText(messageEntity.getMessageBody());
        holder.messageSenderTextView.setText(messageEntity.getMessageSender());

        holder.messageSenderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageItemActionListener.onMessageSenderClick(messageEntity, messageEntity.getMessageSender());
            }
        });

        try{
            if (messageEntity.getMessageMonthYear()!=null && !messageEntity.getMessageMonthYear().trim().isEmpty()){
                holder.messageSectionTextView.setText(messageEntity.getMessageMonthYear());
                holder.messageSectionTextView.setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageDataMList.size();
    }
}
