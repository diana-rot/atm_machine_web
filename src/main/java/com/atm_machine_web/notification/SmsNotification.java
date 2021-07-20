package com.atm_machine_web.notification;

import java.text.MessageFormat;

public class SmsNotification extends Notification{
    StringBuilder phoneNr ;

    public SmsNotification(TypeNotification type, StringBuilder notificationMessage,StringBuilder phoneNr) {
        super(type, notificationMessage);
       this.phoneNr = new StringBuilder();
        this.phoneNr.append(phoneNr);


    }


    @Override
    public void printNotification() {
        System.out.println(MessageFormat.format("{0}Notiication of Type: {1}\n{2}", this.phoneNr + "\n", type, notificationMessage));

    }
}
