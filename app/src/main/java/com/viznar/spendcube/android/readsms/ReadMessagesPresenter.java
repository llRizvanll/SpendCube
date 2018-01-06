package com.viznar.spendcube.android.readsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jrizvan on 1/6/18.
 */

public class ReadMessagesPresenter {

    private Activity activity;
    private ReadMessageListener readMessageListener;
    private String LOG_TAG = "READ_MESSAGES";
    private List<MessageDataM> messageDataMList = new ArrayList<>();

    public ReadMessagesPresenter(ReadMessageListener readMessageListener, Activity activity) {
        this.readMessageListener = readMessageListener;
        this.activity = activity;
    }

    public void syncSMSData(){
        Uri inboxUri = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[] {"address", "date", "body", "read"};

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = activity.getContentResolver();

        Cursor cursor = cr.query(inboxUri, reqCols, null, null, null);

        //Log.d(LOG_TAG, "ALL MESSAGES : "+ DatabaseUtils.dumpCursorToString(cursor));

        if (cursor.moveToFirst()) {
            do {
                String msgBody = "",msgSender = "",msgDate = "";
                Integer msgReadStatus = 0;
                for (int i = 0; i < cursor.getColumnCount(); i++) {

                    if (cursor.getColumnName(i).equals("date")) {

                        long milliSeconds = Long.parseLong(cursor.getString(i));
                        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss aa");
                        Date resultDate = new Date(milliSeconds);
                        msgDate = formatter.format(resultDate);

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

                messageDataMList.add(new MessageDataM(msgBody,msgSender,msgDate,msgReadStatus));

            } while (cursor.moveToNext());

            readMessageListener.onSuccess(messageDataMList);
        }
    }

    private void showMessagesView(){

    }

}
