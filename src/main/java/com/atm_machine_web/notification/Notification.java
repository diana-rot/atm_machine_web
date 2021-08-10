package com.atm_machine_web.notification;

public abstract class Notification {
    TypeNotification type;
    String notificationMessage;

    public Notification(TypeNotification type, String notificationMessage) {
        this.type = type;
        this.notificationMessage = notificationMessage;
    }

    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public enum TypeNotification {
        Critical,
        Warning,
        StockAllert,
        WithdrawOver200,
        defaultNotification
    }
    public abstract void printNotification();


}
