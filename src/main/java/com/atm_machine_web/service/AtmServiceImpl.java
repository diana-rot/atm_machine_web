package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;

import com.atm_machine_web.repo.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {
    @Autowired
    AtmRepository atmRepository;


    @Override
    public Atm findAtmByAtmId(Atm atmId) {
        return atmRepository.findAtmByAtmId( atmId);
    }
}