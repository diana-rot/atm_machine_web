package com.atm_machine_web.entity;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;

import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Entity(name = "Atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stacks")
    List<Stacks> stacks;

    @Column(name = "atm_money",updatable = true)
    Float atmMoney;


    public Atm() {

    }

    public Atm(Float atmMoney) {
        this.atmMoney = atmMoney;
    }

    public Float getAtmMoney() {
        return atmMoney;
    }

    public void setAtmMoney(Float atmMoney) {
        this.atmMoney = atmMoney;
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


    public StringBuilder messageAfterUpdateStacks(List<Notes> availableNotes, Integer nrNotes) {
        StringBuilder returnMessage = new StringBuilder("Au fost adaugate urmatoarele bancnote in atm:" + "\n");
        for (Stacks stack : stacks) {
            returnMessage.append(stack.getNote().getType() + "count updated" + stack.getCount() + "\n");
        }
        return returnMessage;

    }

    public Float updateAtmMoneyFromNotes(Float atmMoney, List<Notes> availableNotes, Integer nrNotes) {
        Float newAtmMoney;
        newAtmMoney = atmMoney;
        for (Notes note : availableNotes) {
            newAtmMoney += note.getValue() * nrNotes;
        }

        return newAtmMoney;
    }

    public StringBuilder updateStacks(List<Notes> availableNotes, Integer nrNotes) {

        StringBuilder testString;
        testString = new StringBuilder("refilledStacks");
        if (stacks.isEmpty()) {
            stacks.add(new Stacks(availableNotes.get(0), nrNotes));//100
            stacks.add(new Stacks(availableNotes.get(1), nrNotes));//50
            stacks.add(new Stacks(availableNotes.get(2), nrNotes));//10
            stacks.add(new Stacks(availableNotes.get(3), nrNotes));//5
            stacks.add(new Stacks(availableNotes.get(4), nrNotes));//1
            Float newAtmMoney = updateAtmMoneyFromNotes(getAtmMoney(), availableNotes, nrNotes);
            setAtmMoney(newAtmMoney);

        } else {
            for (Notes iteratorNote : availableNotes) {
                for (Stacks stack : stacks) {
                    if (stack.getNote().getType().equals(iteratorNote.getType())) {
                        stack.increaseCount(nrNotes);
                        Float newAtmMoney = getAtmMoney() + nrNotes * stack.getNote().getValue();
                        setAtmMoney(newAtmMoney);

                    }
                }
            }
        }
        return testString;
    }

    public StringBuilder refillStackNote(Notes note, Integer nrNotes) {

        StringBuilder messageReturn = new StringBuilder("refillStackNote");
        if (stacks.isEmpty()) {
            stacks.add(new Stacks(note, nrNotes));
        } else {
            for (Stacks stack : stacks) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(nrNotes);

                }
            }
        }
        return messageReturn;

    }

    public StringBuilder messageAfterWithdraw(List<Integer> noteWithdrawer, Integer sumToBeExtracted) {
        StringBuilder returnMessage = new StringBuilder("din suma" + sumToBeExtracted);
        returnMessage.append("au fost extrase bancnotele : " + "\n");
        returnMessage.append("You have extracted" + "\n" + noteWithdrawer.toString());
        return returnMessage;
    }

//    public List<Integer> countWithdraw(Integer sumToBeExtracted) {
//
//        List<Integer> noteWithdrawCounter = new ArrayList();
//        noteWithdrawCounter.addAll(Arrays.asList(0, 0, 0, 0, 0, 0));
//
//        for (Stacks stack : stacks) {
//            Integer currentNoteValue = stack.getNote().getValue();
//
//            if (isCounterNotesGreaterOrEqualThanRest(sumToBeExtracted, currentNoteValue)) {
//                Integer indexOfCurrentExtractedNote = stacks.indexOf(stack);
//                Integer restValueNote = sumToBeExtracted / currentNoteValue;
//
//                if (isCounterNotesGreaterThanRest(stack.getCount(), restValueNote)) {
//
//                    noteWithdrawCounter.set(indexOfCurrentExtractedNote, restValueNote);
//                    sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
//                            noteWithdrawCounter.get(indexOfCurrentExtractedNote) * currentNoteValue);
//                    stack.decreaseCount(noteWithdrawCounter.get(indexOfCurrentExtractedNote));
//
//                } else if (isCounterNotesGreaterThanRest(stack.getCount(), 0)) {
//                    while (stack.getCount() != 0) {
//                        sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
//                                currentNoteValue);
//
//                        stack.decreaseCount(1);
//                        Integer element = noteWithdrawCounter.get(indexOfCurrentExtractedNote);
//                        noteWithdrawCounter.set(indexOfCurrentExtractedNote, element + 1);
//                    }
//                }
//            }
//
//        }
//
//        return noteWithdrawCounter;
//
//    }


    public Boolean isCounterNotesGreaterThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote > restValueNote) {
            return true;
        } else return false;
    }

    public Integer updateSumToBeextracted(Integer sumToBeExtracted, Integer noteValue) {
        sumToBeExtracted -= noteValue;
        return sumToBeExtracted;

    }

    public Boolean isCounterNotesGreaterOrEqualThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote >= restValueNote) {
            return true;
        } else return false;
    }




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
