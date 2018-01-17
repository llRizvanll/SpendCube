package com.viznar.spendcube.android.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;

import com.viznar.spendcube.android.data.db.MessageEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by jrizvan on 1/15/18.
 */

public interface LocalRepository {

    LiveData<List<MessageEntity>> getMessages();

    List<MessageEntity> getAllMessages();

    List<MessageEntity> getMessageEntityBySender(String messageSender);

    void insertMessage(MessageEntity messageEntity);

    void insertMessages(List<MessageEntity> messageEntities);

    void deleteAllMessages();
}
