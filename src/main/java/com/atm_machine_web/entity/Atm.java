package com.atm_machine_web.entity;

import com.atm_machine_web.model.Notes;

import com.atm_machine_web.model.Stacks;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Entity(name = "Atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long atmId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stacks")
        List<Stacks> stacks;
    boolean stackInitialized;

//    Notes note;
//    Map<Notes, Integer> mapNotesLimit = new HashMap<>();
//
//    List<Integer> notes = new ArrayList<>();
//    List<Integer> noteFrequency = new ArrayList<>();
//    Integer nr_notes;


    public Atm(Long atmId, List<Stacks> stacks, boolean stackInitialized) {
        this.atmId = atmId;
        this.stacks = stacks;
        this.stackInitialized = stackInitialized;
    }

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }

    public boolean isStackInitialized() {
        return stackInitialized;
    }

    public void setStackInitialized(boolean stackInitialized) {
        this.stackInitialized = stackInitialized;
    }

    public Atm() {

//        mapNotesLimit.put(new Notes("LEU_100",100), 50);
//        mapNotesLimit.put(new Notes("LEU_50",50), 50);
//        mapNotesLimit.put(new Notes("LEU_10",10), 100);
//        mapNotesLimit.put(new Notes("LEU_5",5), 100);
//        mapNotesLimit.put(new Notes("LEU_1",1), 100);
    }

//    public List<Integer> getNoteFrequency() {
//        return noteFrequency;
//    }
//
//    public List<Integer> getNotes() {
//        return notes;
//    }
//
//    public Integer RestTotalNotes(List<Integer> noteFrequency) {
//        Integer totalCountNotes = 0;
//        for (Integer counterNote : noteFrequency) {
//            totalCountNotes += counterNote;
//        }
//        return totalCountNotes;
//    }
//
//    public void addMoney(List<Integer> sumToBeAdded) {
//
//        for (int i = 0; i < sumToBeAdded.size(); i++) {
//
//            noteFrequency.set(i, noteFrequency.get(i) + sumToBeAdded.get(i));
//        }
//
//    }
//
//    public StringBuilder printCurrency(Integer sum, int nr_notes, int[] noteCounter, List<Integer> noteFrequency, List<Integer> notes) {
//        StringBuilder message = new StringBuilder();
//        message.append("Notes Count : ");
//        for (int i = 0; i < nr_notes; i++) {
//            if (noteCounter[i] != 0) {
//                message.append(notes.get(i)).append(" : ").append(noteCounter[i]).append(" ");
//                message.append(System.getProperty("line.separator"));
//                message.append("left cash amount:").append
//                        (noteFrequency.get(i)).append(":").append(notes.get(i)).append(" ");
//            }
//        }
//        //showIfAllert(sum);
//        return message;
//    }
//
//    public StringBuilder countCurrency(Integer sum, int nr_notes, List<Integer> notes, List<Integer> noteFrequency) {
//        int noteCounter[] = new int[5];
//        Integer newNote;
//        int index;
//        for (Integer note : notes) {
//            if (sum >= note) {
//                newNote = sum / note;
//                index = notes.indexOf(note);
//                if (noteFrequency.get(index) - newNote > 0) {
//
//                    noteCounter[index] = sum / notes.get(index);
//                    sum = sum - noteCounter[index] * notes.get(index);
//                    noteFrequency.set(index, noteCounter[index]);
//                } else if (noteFrequency.get(index) > 0) {
//                    while (noteFrequency.get(index) != 0) {
//                        sum = sum - notes.get(index);
//                        noteFrequency.set(index, noteFrequency.get(index) - 1);
//                        noteCounter[index]++;
//                    }
//                }
//
//            }
//        }
//
//        return printCurrency(sum,nr_notes, noteCounter, noteFrequency, notes);
//    }
//
//    public Integer PercentageOfSum(Integer note, Integer percentage) {
//
//        return(percentage /100)*mapNotesLimit.get(note);
//
//    }

//    public void showIfAllert(Integer sum) {
//
//
//
//        if (noteFrequency.get(0) <= PercentageOfSum(100, 10)) {
//            (new SmsNotification(Notification.TypeNotification.Critical,
//                    new StringBuilder("100 LEI bills under 10% of max"),new StringBuilder("345"))).printNotification();
//        } else if( noteFrequency.get(0) < PercentageOfSum(100, 20)) {
//            (new EmailNotification(Notification.TypeNotification.Warning,
//                    new StringBuilder("100 LEI bills under 20% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//        }
//       if( noteFrequency.get(1) <= PercentageOfSum(50, 15)) {
//           (new EmailNotification(Notification.TypeNotification.Warning,
//                   new StringBuilder("50 lei bills under 15% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//
//
//        }
//        if (RestTotalNotes(noteFrequency) == 0){
//            (new EmailNotification(Notification.TypeNotification.StockAllert,
//                    new StringBuilder("O stock of bills, please refill!"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//        }
//        if(sum >= 200){
//            (new SmsNotification(Notification.TypeNotification.WithdrawOver200,
//                    new StringBuilder("You want to extract over 200 lei from ATM<>, if it s not you, URGENTLY CONTACT THE BANK"),
//                    new StringBuilder("0767893240"))).printNotification();
//        }








}
