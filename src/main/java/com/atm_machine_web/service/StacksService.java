package com.atm_machine_web.service;

import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;

import java.util.List;

public interface StacksService {
    Integer findCountByNote(Notes note);
    List<Stacks> findAllByNote(Notes note);
}
