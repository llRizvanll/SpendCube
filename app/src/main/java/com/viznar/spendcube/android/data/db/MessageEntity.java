package com.viznar.spendcube.android.data.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by jrizvan on 1/15/18.
 */

@Entity
public class MessageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String messageBody;
    private String messageSender;
    private String messageTime;
    private Integer messageReadStatus;
    private String messageMonthYear;
    private String messageMonth;
    private String messageYear;
    private String messageDay;

    public String getMessageMonth() {
        return messageMonth;
    }

    public void setMessageMonth(String messageMonth) {
        this.messageMonth = messageMonth;
    }

    public String getMessageYear() {
        return messageYear;
    }

    public void setMessageYear(String messageYear) {
        this.messageYear = messageYear;
    }

    public String getMessageDay() {
        return messageDay;
    }

    public void setMessageDay(String messageDay) {
        this.messageDay = messageDay;
    }

    public MessageEntity(String messageBody, String messageSender, String messageTime, Integer messageReadStatus) {
        this.messageBody = messageBody;
        this.messageSender = messageSender;
        this.messageTime = messageTime;
        this.messageReadStatus = messageReadStatus;
    }

    public String getMessageMonthYear() {
        return messageMonthYear;
    }

    public void setMessageMonthYear(String messageMonthYear) {
        this.messageMonthYear = messageMonthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public Integer getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setMessageReadStatus(Integer messageReadStatus) {
        this.messageReadStatus = messageReadStatus;
    }
}
