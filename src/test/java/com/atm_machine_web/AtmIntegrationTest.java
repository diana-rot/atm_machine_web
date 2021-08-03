package com.atm_machine_web;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.repo.NotesRepository;
import com.atm_machine_web.service.AtmService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AtmIntegrationTest {
    @Mock
    NotesRepository underTest;
    @Mock
    Atm atm;
    @InjectMocks
    AtmService atmService;

    @Test
    void addNewNote() {

    }


}
