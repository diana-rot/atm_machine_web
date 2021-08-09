package com.atm_machine_web.repo;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtmRepository extends JpaRepository<Atm, Long> {
    Atm findAtmByAtmId(Long AtmId);



}
