package com.atm_machine_web.service;

import com.atm_machine_web.model.Notes;

import java.util.List;

public interface NotesService {
    Integer findValueByType(String type);
    List<Notes> findAll();
    Notes findNotesByType(String noteType);

}
