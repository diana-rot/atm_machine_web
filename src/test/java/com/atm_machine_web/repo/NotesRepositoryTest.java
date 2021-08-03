package com.atm_machine_web.repo;

import com.atm_machine_web.model.Notes;
import com.atm_machine_web.service.NotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NotesRepositoryTest {
    @Mock
    NotesRepository underTest;
    @Mock
    Notes note;
    @InjectMocks
    NotesService notesService;


    @Test
    void itShouldfindValueByType() {
        //given
        String type = new String("Leu_10");
        //when
       // when(underTest.getById().thenReturn(notesService)
        when(underTest.getById(any())).thenReturn(note);
        Integer expectedValue = 10;
        Integer value = underTest.findValueByType("type");
        System.out.println(value + "valoare");
        //then
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test
    void findAll() {
    }
}