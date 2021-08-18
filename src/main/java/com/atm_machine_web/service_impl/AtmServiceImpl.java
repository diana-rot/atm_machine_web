package com.atm_machine_web.service_impl;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.notification.EmailNotification;
import com.atm_machine_web.notification.Notification;
import com.atm_machine_web.notification.SmsNotification;
import com.atm_machine_web.repo.AtmRepository;
import com.atm_machine_web.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService {
    @Autowired
    AtmRepository atmRepository;

    @Override
    public Atm findAtmByAtmId(Long atmId) {
        return atmRepository.findAtmByAtmId(atmId);
    }

    @Override
    public Atm save(Atm atm) {
        return atmRepository.save(atm);
    }


    @Override
    public Float findTotalAtmMoney(Atm atm) {
        return atm.getAtmMoney();

    }

    @Override
    public List<Integer> countWithdraw(Atm atm, List<Stacks> stacks, Float sold, Integer sumToBeExtracted) {

        Float newAtmMoney = atm.getAtmMoney() - sumToBeExtracted;
        atm.setAtmMoney(newAtmMoney);

        List<Integer> noteWithdrawCounter = new ArrayList();
        noteWithdrawCounter.addAll(Arrays.asList(0, 0, 0, 0, 0, 0));
        if (sold < sumToBeExtracted) {
            System.out.println("Insufficient funds");
            return null;
        }
        for (Stacks stack : atm.getStacks()) {
            Integer currentNoteValue = stack.getNote().getValue();

            if (isNoteMoreThanCoveringTheSum(sumToBeExtracted, currentNoteValue)) {
                Integer indexOfCurrentExtractedNote = atm.getStacks().indexOf(stack);
                Integer restValueNote = sumToBeExtracted / currentNoteValue;

                if (isValueOfNoteCoveringTheSum(stack.getCount(), restValueNote)) {

                    noteWithdrawCounter.set(indexOfCurrentExtractedNote, restValueNote);
                    sumToBeExtracted = updateSumToBeExtracted(sumToBeExtracted,
                            noteWithdrawCounter.get(indexOfCurrentExtractedNote) * currentNoteValue);
                    stack.decreaseCount(noteWithdrawCounter.get(indexOfCurrentExtractedNote));

                } else if (isValueOfNoteCoveringTheSum(stack.getCount(), 0)) {
                    while (stack.getCount() != 0) {
                        sumToBeExtracted = updateSumToBeExtracted(sumToBeExtracted,
                                currentNoteValue);

                        stack.decreaseCount(1);
                        Integer element = noteWithdrawCounter.get(indexOfCurrentExtractedNote);
                        noteWithdrawCounter.set(indexOfCurrentExtractedNote, element + 1);
                    }
                }
            }

        }

        atmRepository.save(atm);

        return noteWithdrawCounter;

    }


    @Override
    public List<Stacks> updateStacks(Atm atm, List<Stacks> stacks, List<Notes> availableNotes, Integer nrNotes) {

        if (stacks.isEmpty()) {
            stacks.add(new Stacks(availableNotes.get(0), nrNotes));//100
            stacks.add(new Stacks(availableNotes.get(1), nrNotes));//50
            stacks.add(new Stacks(availableNotes.get(2), nrNotes));//10
            stacks.add(new Stacks(availableNotes.get(3), nrNotes));//5
            stacks.add(new Stacks(availableNotes.get(4), nrNotes));//1
            Float newAtmMoney = updateAtmMoneyFromNotes(atm.getAtmMoney(), availableNotes, nrNotes);
            atm.setAtmMoney(newAtmMoney);
            System.out.println("alo"+newAtmMoney);


        } else {

            for (Notes iteratorNote : availableNotes) {
                for (Stacks stack : atm.getStacks()) {
                    if (stack.getNote().getType().equals(iteratorNote.getType())) {
                        stack.increaseCount(nrNotes);
                        Float newAtmMoney = atm.getAtmMoney() + nrNotes * stack.getNote().getValue();
                        atm.setAtmMoney(newAtmMoney);
                        System.out.println("alo"+newAtmMoney);

                    }
                }
            }
        }

        atmRepository.save(atm);

        return stacks;
    }


    @Override
    public Atm refillStackNote(Notes note, Integer nrNotes) {
        Atm atm = atmRepository.findAtmByAtmId(1L);

        if (atm.getStacks() == null) {
            List<Stacks> stacks = new ArrayList<Stacks>();
            stacks.add(new Stacks(new Notes("Leu_100", 100), nrNotes));//100
            stacks.add(new Stacks(new Notes("Leu_50", 50), nrNotes));//100
            stacks.add(new Stacks(new Notes("Leu_10", 10), nrNotes));//100
            stacks.add(new Stacks(new Notes("Leu_5", 5), nrNotes));//100
            stacks.add(new Stacks(new Notes("Leu_1", 1), nrNotes));
            atm.setStacks(stacks);

        } else {
            for (Stacks stack : atm.getStacks()) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(nrNotes);
                    Float newAtmMoney = atm.getAtmMoney() + nrNotes * stack.getNote().getValue();
                    atm.setAtmMoney(newAtmMoney);


                }
            }

        }
        atm = atmRepository.save(atm);
        System.out.println(atm.getStacks());
        return atmRepository.save(atm);


    }


    @Override
    public Float updateAtmMoneyFromNotes(Float atmMoney, List<Notes> availableNotes, Integer nrNotes) {
        Float newAtmMoney;
        newAtmMoney = atmMoney;
        for (Notes note : availableNotes) {
            newAtmMoney += note.getValue() * nrNotes;
        }

        return newAtmMoney;
    }


    public Boolean isValueOfNoteCoveringTheSum(Integer counterNote, Integer restValueNote) {
        if (counterNote > restValueNote) {
            return true;
        } else return false;
    }

    public Integer updateSumToBeExtracted(Integer sumToBeExtracted, Integer noteValue) {
        sumToBeExtracted -= noteValue;
        return sumToBeExtracted;

    }

    public Boolean isNoteMoreThanCoveringTheSum(Integer counterNote, Integer restValueNote) {
        if (counterNote >= restValueNote) {
            return true;
        } else return false;
    }

    public List<Stacks> getBalance() {
        Atm atm = atmRepository.findAtmByAtmId(1L);
        save(atm);
        return atm.getStacks();


    }

    @Override
    public Notification showIfAlert(Integer sum, List<Stacks> stacks,Float atmMoney) {
        EmailNotification byDefault = new EmailNotification(Notification.TypeNotification.defaultNotification, "no", "email");
        if (atmMoney == 0) {
            return (new EmailNotification(Notification.TypeNotification.StockAllert,
                    new String("O stock of bills, please refill!"), new String("help_center_bank@gmail.com")));
        }


        if ( stacks.get(0).getCount() <= 1) {
         return   (new SmsNotification(Notification.TypeNotification.Critical,
                    new String("Bank: Only one bill of 100 LEI,refill "), new String("345")));
        } else if (stacks.get(0).getCount() == 2) {
          return  (new EmailNotification(Notification.TypeNotification.Warning,
                    new String("Bank: Only 2 bills of 100 LEI bills, refill!"), new String("help_center_bank@gmail.com")));
        }
        if (stacks.get(0).getCount() == 2) {
           return  (new EmailNotification(Notification.TypeNotification.Warning,
                    new String("50 lei bills under 15% of max"), new String("help_center_bank@gmail.com")));

        }
        if (sum >= 200) {
            return (new SmsNotification(Notification.TypeNotification.WithdrawOver200,
                    new String("You want to extract over 200 lei from ATM<>," +
                            " if it s not you, URGENTLY CONTACT THE BANK"), "073289392832"));


        }

        return byDefault;
    }
}