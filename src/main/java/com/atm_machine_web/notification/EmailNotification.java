package com.atm_machine_web.notification;


import java.text.MessageFormat;

public class EmailNotification extends Notification{
StringBuilder email;

    public EmailNotification(TypeNotification type, String notificationMessage, String email ) {
        super(type, notificationMessage);
        this.email = new StringBuilder();
        this.email.append(email);

    }

    @Override
    public void printNotification() {
        System.out.println(MessageFormat.format("{0}Notiication of Type: {1}\n{2}", this.email + "\n", type, notificationMessage));

    }



}
