package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;

import java.util.List;

public interface AtmService {
    Atm findAtmByAtmId(Long atmId);

    Atm save(Atm atm);

   // List<Integer> countWithdraw(Float sold, Integer sumToBeExtracted);

    //    @Override
    ////    public List<Integer> countWithdraw(Float sold, Integer sumToBeExtracted) {
    ////        return null;
    ////    }
    Float findTotalAtmMoney(Atm atm);

    List<Integer> countWithdraw(Atm atm, List<Stacks> stacks, Float sold, Integer sumToBeExtracted);

    List<Stacks> updateStacks(Atm atm, List<Stacks> stacks, List<Notes> availableNotes, Integer nrNotes);


    Atm refillStackNote(Notes note, Integer nrNotes);

    Float updateAtmMoneyFromNotes(Float atmMoney, List<Notes> availableNotes, Integer nrNotes);
}
