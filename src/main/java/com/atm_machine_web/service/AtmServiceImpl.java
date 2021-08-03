package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;

import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.repo.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
                    sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
                            noteWithdrawCounter.get(indexOfCurrentExtractedNote) * currentNoteValue);
                    stack.decreaseCount(noteWithdrawCounter.get(indexOfCurrentExtractedNote));

                } else if (isCounterNotesGreaterThanRest(stack.getCount(), 0)) {
                    while (stack.getCount() != 0) {
                        sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
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

//
//    @Override
//    public void doStuff(Integer sumToBeExtracted) {
//        Atm atm = atmRepository.findAtmByAtmId(1L);
//        List<Integer> noteWithdrawCounter = new ArrayList();
//        noteWithdrawCounter.addAll(Arrays.asList(0, 0, 0, 0, 0, 0));
//
//        for (Stacks stack : atm.getStacks()) {
//            Integer currentNoteValue = stack.getNote().getValue();
//
//            if (isCounterNotesGreaterOrEqualThanRest(sumToBeExtracted, currentNoteValue)) { //
//                Integer indexOfCurrentExtractedNote = atm.getStacks().indexOf(stack);
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
////        return noteWithdrawCounter;
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

}