package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.repo.NotesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AtmServiceTest {
    @Mock
    NotesRepository underTest;
    @Mock
    Atm atm;
    @InjectMocks
    AtmService atmService;

        @Test
    void findAtmByAtmId() {
    }

    @Test
    void save() {
    }
}