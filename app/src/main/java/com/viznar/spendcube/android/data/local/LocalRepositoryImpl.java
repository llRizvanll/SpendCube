package com.viznar.spendcube.android.data.local;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.viznar.spendcube.android.data.dao.MessagesDAO;
import com.viznar.spendcube.android.data.db.DatabaseCreator;
import com.viznar.spendcube.android.data.db.MessageEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by jrizvan on 1/15/18.
 */

public class LocalRepositoryImpl implements LocalRepository {

    private MessagesDAO messagesDAO ;


    public LocalRepositoryImpl(Context context) {
        messagesDAO = DatabaseCreator.getMessagesDatabase(context).messagesDatabase();
        //executor = Executors.newFixedThreadPool(2);
    }

    public LocalRepositoryImpl(MessagesDAO messagesDAO, Executor executor) {
        this.messagesDAO = messagesDAO;
        //this.executor = executor;
    }

    @Override
    public LiveData<List<MessageEntity>> getMessages() {
        return messagesDAO.getMessages();
    }

    @Override
    public List<MessageEntity> getAllMessages() {
        return messagesDAO.getAllMessages();
    }

    @Override
    public List<MessageEntity> getMessageEntityBySender(String messageSender) {
        return messagesDAO.getMessageEntityBySender(messageSender);
    }

    @Override
    public void insertMessage(final MessageEntity messageEntity) {
        messagesDAO.insertMessage(messageEntity);
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                messagesDAO.insertMessage(messageEntity);
//            }
//        });
    }

    @Override
    public void insertMessages(final List<MessageEntity> messageEntities) {
        messagesDAO.insertMessages(messageEntities);
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                messagesDAO.insertMessages(messageEntities);
//            }
//        });
    }

    @Override
    public void deleteAllMessages() {
        messagesDAO.deleteAllMessages();
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                messagesDAO.deleteAllMessages();
//            }
//        });
    }
}
