package com.atm_machine_web.notification;

public abstract class Notification {
    TypeNotification type;
    StringBuilder notificationMessage;

    public Notification(TypeNotification type, StringBuilder notificationMessage) {
        this.type = type;
        this.notificationMessage = notificationMessage;
    }

    public enum TypeNotification {
        Critical,
        Warning,
        StockAllert,
        WithdrawOver200
    }
    public abstract void printNotification();


}
