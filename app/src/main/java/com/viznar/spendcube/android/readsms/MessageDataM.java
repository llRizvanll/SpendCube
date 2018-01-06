package com.viznar.spendcube.android.readsms;

/**
 * Created by jrizvan on 1/6/18.
 */

public class MessageDataM {

    private String msgBody;
    private String msgSender;
    private String msgDate;
    private Integer msgReadStatus;

    public MessageDataM(String msgBody, String msgSender, String msgDate, Integer msgReadStatus) {
        this.msgBody = msgBody;
        this.msgSender = msgSender;
        this.msgDate = msgDate;
        this.msgReadStatus = msgReadStatus;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }


    public Integer getMsgReadStatus() {
        return msgReadStatus;
    }

    public void setMsgReadStatus(Integer msgReadStatus) {
        this.msgReadStatus = msgReadStatus;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }
}
