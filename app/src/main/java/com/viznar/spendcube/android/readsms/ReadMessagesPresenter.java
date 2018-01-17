package com.viznar.spendcube.android.readsms;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.viznar.spendcube.android.data.db.MessageEntity;
import com.viznar.spendcube.android.data.local.LocalRepository;
import com.viznar.spendcube.android.data.local.LocalRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public class ReadMessagesPresenter {

    private Activity activity;
    private ReadMessageListener readMessageListener;
    private String LOG_TAG = "READ_MESSAGES";
    private List<MessageEntity> messageEntities = new ArrayList<>();
    private LocalRepository localRepository;

    public ReadMessagesPresenter(ReadMessageListener readMessageListener, Activity activity) {
        this.readMessageListener = readMessageListener;
        this.activity = activity;
        this.localRepository = new LocalRepositoryImpl(activity);
    }

    public void syncSMSData(){
        Uri inboxUri = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[] {"address", "date", "body", "read"};

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = activity.getContentResolver();

        Cursor cursor = cr.query(inboxUri, reqCols, null, null, null);

        Log.d(LOG_TAG, "ALL MESSAGES : "+ DatabaseUtils.dumpCursorToString(cursor));

        if (cursor.moveToFirst()) {
            do {
                String msgBody = "",msgSender = "",msgDate = "", millis ;
                Integer msgReadStatus = 0;
                Integer month = 1 , year = 2018, day = 1;
                for (int i = 0; i < cursor.getColumnCount(); i++) {

                    if (cursor.getColumnName(i).equals("date")) {

                        long milliSeconds = Long.parseLong(cursor.getString(i));
                        /*SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss aa");
                        Date resultDate = new Date(milliSeconds);
                        msgDate = formatter.format(resultDate);*/
                        Calendar c = Calendar.getInstance();
                        //Set time in milliseconds
                        c.setTimeInMillis(Long.valueOf(milliSeconds));
                        year = c.get(Calendar.YEAR);
                        month = c.get(Calendar.MONTH)+1;
                        day = c.get(Calendar.DAY_OF_MONTH);
                        /*int hr = c.get(Calendar.HOUR);
                        int min = c.get(Calendar.MINUTE);
                        int sec = c.get(Calendar.SECOND);*/

                    } else if (cursor.getColumnName(i).equals("address")) {

                        msgSender = cursor.getString(i);

                    }
                    else if (cursor.getColumnName(i).equals("read")) {

                        msgReadStatus = 0;
                    }
                    else {
                        msgBody = cursor.getString(i);
                    }
                }

                //messageEntities.add(new MessageDataM(msgBody,msgSender,msgDate,msgReadStatus));
                MessageEntity messageEntityObj = new MessageEntity(msgBody,msgSender,msgDate,msgReadStatus);
                try{
                    messageEntityObj.setMessageMonth(String.valueOf(month));
                    messageEntityObj.setMessageDay(String.valueOf(day));
                    messageEntityObj.setMessageYear(String.valueOf(year));
                    messageEntityObj.setMessageMonthYear(String.valueOf(day)+" - "+String.valueOf(month)+" - "+String.valueOf(year));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                messageEntities.add(messageEntityObj);

            } while (cursor.moveToNext());

            try{
                localRepository.deleteAllMessages();
                localRepository.insertMessages(messageEntities);

                loadMessagesOntoList();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void loadMessagesOntoList(){
        readMessageListener.onSuccess(localRepository.getAllMessages());
    }

    public void searchQuery(String searchTerm){
        if (localRepository.searchKeywordInMessages(searchTerm)!=null){
            readMessageListener.onSuccess(localRepository.searchKeywordInMessages(searchTerm));
        }
    }
}
