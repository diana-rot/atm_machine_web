package com.atm_machine_web.entity;

import com.atm_machine_web.notification.EmailNotification;
import com.atm_machine_web.notification.Notification;
import com.atm_machine_web.notification.SmsNotification;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Atm {
    Map<Integer, Integer> mapNotesLimit = new HashMap<>();
    List<Integer> notes = new ArrayList<>();
    List<Integer> noteFrequency = new ArrayList<>();
    Integer nr_notes;

    private Atm() {
        mapNotesLimit.put(100, 50);
        mapNotesLimit.put(50, 50);
        mapNotesLimit.put(10, 100);
        mapNotesLimit.put(5, 100);
        mapNotesLimit.put(1, 100);
        notes.add(100);
        notes.add(50);
        notes.add(10);
        notes.add(5);
        notes.add(1);
        //for currency
        noteFrequency.add(50);
        noteFrequency.add(50);
        noteFrequency.add(100);
        noteFrequency.add(100);
        noteFrequency.add(100);
        nr_notes = 5;

    }

    public List<Integer> getNoteFrequency() {
        return noteFrequency;
    }

    public List<Integer> getNotes() {
        return notes;
    }

    public Integer RestTotalNotes(List<Integer> noteFrequency) {
        Integer totalCountNotes = 0;
        for (Integer counterNote : noteFrequency) {
            totalCountNotes += counterNote;
        }
        return totalCountNotes;
    }

    public void addMoney(List<Integer> sumToBeAdded) {

        for (int i = 0; i < sumToBeAdded.size(); i++) {

            noteFrequency.set(i, noteFrequency.get(i) + sumToBeAdded.get(i));
        }

    }

    public StringBuilder printCurrency(Integer sum, int nr_notes, int[] noteCounter, List<Integer> noteFrequency, List<Integer> notes) {
        StringBuilder message = new StringBuilder();
        message.append("Notes Count : ");
        for (int i = 0; i < nr_notes; i++) {
            if (noteCounter[i] != 0) {
                message.append(notes.get(i)).append(" : ").append(noteCounter[i]).append(" ");
                message.append(System.getProperty("line.separator"));
                message.append("left cash amount:").append
                        (noteFrequency.get(i)).append(":").append(notes.get(i)).append(" ");
            }
        }
        showIfAllert(sum);
        return message;
    }

    public StringBuilder countCurrency(Integer sum, int nr_notes, List<Integer> notes, List<Integer> noteFrequency) {
        int noteCounter[] = new int[5];
        Integer newNote;
        int index;
        for (Integer note : notes) {
            if (sum >= note) {
                newNote = sum / note;
                index = notes.indexOf(note);
                if (noteFrequency.get(index) - newNote > 0) {

                    noteCounter[index] = sum / notes.get(index);
                    sum = sum - noteCounter[index] * notes.get(index);
                    noteFrequency.set(index, noteCounter[index]);
                } else if (noteFrequency.get(index) > 0) {
                    while (noteFrequency.get(index) != 0) {
                        sum = sum - notes.get(index);
                        noteFrequency.set(index, noteFrequency.get(index) - 1);
                        noteCounter[index]++;
                    }
                }

            }
        }

        return printCurrency(sum,nr_notes, noteCounter, noteFrequency, notes);
    }

    public Integer PercentageOfSum(Integer note, Integer percentage) {

        return(percentage /100)*mapNotesLimit.get(note);

    }

    public void showIfAllert(Integer sum) {


//        if (LeftNrofNotesfNotes(noteFrequency) == 0) {
//            message = StockAlert.O_STOCK;
//        } else {
        if (noteFrequency.get(0) <= PercentageOfSum(100, 10)) {
            (new SmsNotification(Notification.TypeNotification.Critical,
                    new StringBuilder("100 LEI bills under 10% of max"),new StringBuilder("345"))).printNotification();
        } else if( noteFrequency.get(0) < PercentageOfSum(100, 20)) {
            (new EmailNotification(Notification.TypeNotification.Warning,
                    new StringBuilder("100 LEI bills under 20% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
        }
       if( noteFrequency.get(1) <= PercentageOfSum(50, 15)) {
           (new EmailNotification(Notification.TypeNotification.Warning,
                   new StringBuilder("50 lei bills under 15% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();


        }
        if (RestTotalNotes(noteFrequency) == 0){
            (new EmailNotification(Notification.TypeNotification.StockAllert,
                    new StringBuilder("O stock of bills, please refill!"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
        }
        if(sum >= 200){
            (new SmsNotification(Notification.TypeNotification.WithdrawOver200,
                    new StringBuilder("You want to extract over 200 lei from ATM<>, if it s not you, URGENTLY CONTACT THE BANK"),
                    new StringBuilder("0767893240"))).printNotification();
        }





}


}
