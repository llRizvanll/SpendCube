package com.viznar.spendcube.android.readsms;

import com.viznar.spendcube.android.data.db.MessageEntity;

/**
 * Created by jrizvan on 1/17/18.
 */

public interface MessageItemActionListener {

    void onMessageSenderClick(MessageEntity messageEntity, String messageSender);

    void onIconClick();

}
