package com.viznar.spendcube.android.readsms;

import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public interface ReadMessageListener {

    void onSuccess(List<MessageDataM> messageData);
    void onError(String message);

}
