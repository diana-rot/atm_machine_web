package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.repo.AtmRepository;
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
    public List<Integer> countWithdraw(Integer sumToBeExtracted) {
        Atm atm = atmRepository.findAtmByAtmId(1L);
        List<Integer> noteWithdrawCounter = new ArrayList();
        noteWithdrawCounter.addAll(Arrays.asList(0, 0, 0, 0, 0, 0));

        for (Stacks stack : atm.getStacks()) {
            Integer currentNoteValue = stack.getNote().getValue();

            if (isCounterNotesGreaterOrEqualThanRest(sumToBeExtracted, currentNoteValue)) {
                Integer indexOfCurrentExtractedNote = atm.getStacks().indexOf(stack);
                Integer restValueNote = sumToBeExtracted / currentNoteValue;

                if (isCounterNotesGreaterThanRest(stack.getCount(), restValueNote)) {

                    noteWithdrawCounter.set(indexOfCurrentExtractedNote, restValueNote);
                    sumToBeExtracted = updateSumToBeExtracted(sumToBeExtracted,
                            noteWithdrawCounter.get(indexOfCurrentExtractedNote) * currentNoteValue);
                    stack.decreaseCount(noteWithdrawCounter.get(indexOfCurrentExtractedNote));

                } else if (isCounterNotesGreaterThanRest(stack.getCount(), 0)) {
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

        return noteWithdrawCounter;

    }

    @Override
    public void updateStacks(List<Notes> availableNotes, Integer nrNotes) {
        Atm atm = atmRepository.findAtmByAtmId(1L);
        if (atm.getStacks().isEmpty()) {
            atm.getStacks().add(new Stacks(availableNotes.get(0), nrNotes));//100
            atm.getStacks().add(new Stacks(availableNotes.get(1), nrNotes));//50
            atm.getStacks().add(new Stacks(availableNotes.get(2), nrNotes));//10
            atm.getStacks().add(new Stacks(availableNotes.get(3), nrNotes));//5
            atm.getStacks().add(new Stacks(availableNotes.get(4), nrNotes));//1
            Float newAtmMoney = updateAtmMoneyFromNotes(atm.getAtmMoney(), availableNotes, nrNotes);
            atm.setAtmMoney(newAtmMoney);

        } else {
            for (Notes iteratorNote : availableNotes) {
                for (Stacks stack : atm.getStacks()) {
                    if (stack.getNote().getType().equals(iteratorNote.getType())) {
                        stack.increaseCount(nrNotes);
                        Float newAtmMoney = atm.getAtmMoney() + nrNotes * stack.getNote().getValue();
                        atm.setAtmMoney(newAtmMoney);

                    }
                }
            }
        }

    }

    @Override
    public void refillStackNote(Notes note, Integer nrNotes) {
        Atm atm = atmRepository.findAtmByAtmId(1L);


        if (atm.getStacks().isEmpty()) {
            atm.getStacks().add(new Stacks(note, nrNotes));
        } else {
            for (Stacks stack : atm.getStacks()) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(nrNotes);
                    Float newAtmMoney = atm.getAtmMoney() + nrNotes * stack.getNote().getValue();
                    atm.setAtmMoney(newAtmMoney);

                }
            }
        }


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


    public Boolean isCounterNotesGreaterThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote > restValueNote) {
            return true;
        } else return false;
    }

    public Integer updateSumToBeExtracted(Integer sumToBeExtracted, Integer noteValue) {
        sumToBeExtracted -= noteValue;
        return sumToBeExtracted;

    }

    public Boolean isCounterNotesGreaterOrEqualThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote >= restValueNote) {
            return true;
        } else return false;
    }

}