package com.viznar.spendcube.android.detail;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toolbar;

import com.viznar.spendcube.android.R;

/**
 * Created by jrizvan on 1/17/18.
 */

public class ItemDetailsActivity extends FragmentActivity {

    private MessageDataPass messageDataPass;
    private ItemDetailsFragment fragmentItemDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = findViewById(R.id.item_detail_toolbar);
        setActionBar(toolbar);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().getBundleExtra("messagebundle").getSerializable("messagedata") instanceof MessageDataPass)
            messageDataPass = (MessageDataPass) getIntent().getBundleExtra("messagebundle").getSerializable("messagedata");

        toolbar.setTitle(messageDataPass.getMessageSender());

        if (savedInstanceState == null) {
            fragmentItemDetail = ItemDetailsFragment.getInstance(messageDataPass);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItemDetail);
            ft.commit();
        }
    }
}
