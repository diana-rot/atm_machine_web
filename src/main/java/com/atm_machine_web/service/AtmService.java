package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.User;

public interface AtmService {
    Atm findAtmByAtmId(Long atmId);

    Atm save(Atm atm);

}
