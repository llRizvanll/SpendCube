package com.viznar.spendcube.android.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.viznar.spendcube.android.data.db.MessageEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by jrizvan on 1/15/18.
 */

@Dao
public interface MessagesDAO {

    @Query("SELECT * FROM messageentity")
    LiveData<List<MessageEntity>> getMessages();

    @Query("SELECT * FROM messageentity")
    List<MessageEntity> getAllMessages();

    @Query("SELECT * FROM messageentity WHERE messageSender = :messageSender")
    List<MessageEntity> getMessageEntityBySender(String messageSender);

    @Query("SELECT * FROM messageentity WHERE messageBody LIKE '%' || :searchTerm || '%' OR messageSender LIKE '%' || :searchTerm || '%' ")
    List<MessageEntity> searchKeywordInMessages(String searchTerm);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessage(MessageEntity messageEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessages(List<MessageEntity> messageEntity);

    @Query("DELETE FROM messageEntity")
    void deleteAllMessages();

}
