package com.viznar.spendcube.android.readsms;

import android.arch.lifecycle.LiveData;

import com.viznar.spendcube.android.data.db.MessageEntity;

import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public interface ReadMessageListener {

    void onSuccess(List<MessageEntity> messageData);
    void onError(String message);

}
