package com.atm_machine_web.notification;

import java.text.MessageFormat;

public class SmsNotification extends Notification{
    String phoneNr ;

    public SmsNotification(TypeNotification type, String notificationMessage,String phoneNr) {
        super(type, notificationMessage);
       this.phoneNr = phoneNr;



    }


    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public void printNotification() {
        System.out.println(MessageFormat.format("{0}Notiication of Type: {1}\n{2}", this.phoneNr + "\n", type, notificationMessage));

    }
}
