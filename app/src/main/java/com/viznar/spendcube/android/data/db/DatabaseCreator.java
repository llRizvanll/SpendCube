package com.viznar.spendcube.android.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by jrizvan on 1/15/18.
 */

public class DatabaseCreator {

    private static MessagesDatabase messagesDatabase;

    private static final Object LOCK = new Object();

    public synchronized static MessagesDatabase getMessagesDatabase(Context context){
        if (messagesDatabase == null){
            synchronized (LOCK){
                if (messagesDatabase == null){
                    messagesDatabase = Room.databaseBuilder(context,MessagesDatabase.class,"messages.db").allowMainThreadQueries().build();
                }
            }
        }
        return messagesDatabase;
    }
}
