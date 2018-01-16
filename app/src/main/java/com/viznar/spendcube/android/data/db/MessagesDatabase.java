package com.viznar.spendcube.android.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.viznar.spendcube.android.data.dao.MessagesDAO;

/**
 * Created by jrizvan on 1/15/18.
 */
@Database(entities = MessageEntity.class, version = 1)
public abstract class MessagesDatabase extends RoomDatabase{
    public abstract MessagesDAO messagesDatabase();
}
