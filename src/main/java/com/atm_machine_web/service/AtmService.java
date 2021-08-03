package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;

import java.util.List;

public interface AtmService {
    Atm findAtmByAtmId(Long atmId);

    Atm save(Atm atm);

    List<Integer> countWithdraw(Integer sumToBeExtracted);

    void updateStacks(List<Notes> availableNotes, Integer nrNotes);

    void refillStackNote(Notes note, Integer nrNotes);

    Float updateAtmMoneyFromNotes(Float atmMoney, List<Notes> availableNotes, Integer nrNotes);
}
