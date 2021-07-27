package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;

public interface AtmService {
    Atm findAtmByAtmId(Atm atmId);
}
