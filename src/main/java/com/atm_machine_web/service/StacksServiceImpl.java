package com.atm_machine_web.service;

import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.repo.StacksRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StacksServiceImpl implements StacksService {
    @Autowired
    StacksRepository stacksRepository;
    @Override
    public Integer findCountByNote(Notes note) {
        return stacksRepository.findCountByNote(note);
    }

    @Override
    public List<Stacks> findAllByNote(Notes note) {
        return  stacksRepository.findAllByNote(note);
    }
}
