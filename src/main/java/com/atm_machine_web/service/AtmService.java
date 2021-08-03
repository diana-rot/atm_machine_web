package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.User;

import java.util.List;

public interface AtmService {
    Atm findAtmByAtmId(Long atmId);

    Atm save(Atm atm);

    List<Integer> countWithdraw(Integer sumToBeExtracted);
}
