package com.viznar.spendcube.android.detail;

import java.io.Serializable;

/**
 * Created by jrizvan on 1/17/18.
 */

public class MessageDataPass implements Serializable {

    String messageSender;

    public MessageDataPass(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }
}
