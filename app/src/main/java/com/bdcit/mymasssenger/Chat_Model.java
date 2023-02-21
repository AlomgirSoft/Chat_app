package com.bdcit.mymasssenger;

public class Chat_Model {

    private String senderId;
    private String rcevaerId;
    private  String message;
    private  String messageId;




    public Chat_Model() {
    }

    public Chat_Model(String senderId, String rcevaerId, String message, String messageId) {
        this.senderId = senderId;
        this.rcevaerId = rcevaerId;
        this.message = message;
        this.messageId = messageId;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRcevaerId() {
        return rcevaerId;
    }

    public void setRcevaerId(String rcevaerId) {
        this.rcevaerId = rcevaerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
