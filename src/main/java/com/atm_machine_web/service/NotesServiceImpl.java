package com.atm_machine_web.service;

import com.atm_machine_web.model.Notes;
import com.atm_machine_web.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    NotesRepository notesRepository;

    @Override
    public Integer findValueByType(String type) {
        return notesRepository.findValueByType(type);
    }

    @Override
    public List<Notes> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public Notes findNotesByType(String noteType) {
        return notesRepository.findNotesByType(noteType);
    }

}
