package com.viznar.spendcube.android.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viznar.spendcube.android.R;
import com.viznar.spendcube.android.data.db.MessageEntity;
import com.viznar.spendcube.android.data.local.LocalRepositoryImpl;
import com.viznar.spendcube.android.readsms.MessageItemActionListener;
import com.viznar.spendcube.android.readsms.MessagesViewAdapter;

import java.util.List;

/**
 * Created by jrizvan on 1/17/18.
 */

public class ItemDetailsFragment extends Fragment {

    private MessageDataPass messageDataPass;
    private MessagesViewAdapter messagesViewAdapter;
    private List<MessageEntity> messageEntityList;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageDataPass =  (MessageDataPass) getArguments().getSerializable("sender");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_detail_list, container, false);

        mRecyclerView =  view.findViewById(R.id.item_detail_listview);

        getMessagesListBySender(messageDataPass.getMessageSender());

        return view;
    }

    public static ItemDetailsFragment getInstance(MessageDataPass messageDataPass){
        ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("sender",messageDataPass);
        itemDetailsFragment.setArguments(bundle);
        return itemDetailsFragment;
    }

    private void getMessagesListBySender(String messageSender){
        if (messageSender!=null) messageEntityList = new LocalRepositoryImpl(getActivity()).getMessageEntityBySender(messageSender);

        if (messagesViewAdapter == null){

            mRecyclerView.setHasFixedSize(true);
            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            messagesViewAdapter = new MessagesViewAdapter(messageEntityList,messageItemActionListener);
            mRecyclerView.setAdapter(messagesViewAdapter);
        }
    }

    MessageItemActionListener messageItemActionListener = new MessageItemActionListener() {
        @Override
        public void onMessageSenderClick(MessageEntity messageEntity, String messageSender) {

        }

        @Override
        public void onIconClick() {

        }
    };
}
