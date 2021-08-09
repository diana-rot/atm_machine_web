package com.atm_machine_web.service;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.repo.AtmRepository;
import com.atm_machine_web.service_impl.AtmServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//import static org.junit.Assert.assertArrayEquals;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AtmServiceTest {
    @Mock
    AtmRepository atmRepository;

    @InjectMocks
    AtmServiceImpl atmService;

    @Spy
    Atm atm = new Atm(1L, (float) 0);

    @Mock
    Notes notes;

    @Mock
    List<Stacks> stacks;


    @Test
    void findAtmByAtmId() {
        when(atmRepository.getById(any())).thenReturn(atm);
        when(atmRepository.save(any())).thenReturn(atm);
        System.out.println(atm.toString());

    }

    @Test
    void MockCorrectSold() {
        List<Stacks> newStacks = new ArrayList<>();
        when(atmRepository.getById(any())).thenReturn(atm);
        atm.setStacks(newStacks);
        when(atmRepository.save(any())).thenReturn(atm);

        List<Notes> notes = new ArrayList<Notes>();
        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));

        newStacks = atmService.updateStacks(atm, newStacks, notes, 1);
        when(atmRepository.save(any())).thenReturn(atm);
        // assertEquals(atm.get);

    }

    @Test
    void findTotalMockAtmMoney() {
        List<Stacks> newStacks = new ArrayList<>();
        when(atmRepository.getById(any())).thenReturn(atm);
        atm.setStacks(newStacks);
        when(atmRepository.save(any())).thenReturn(atm);

        List<Notes> notes = new ArrayList<Notes>();
        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));
        newStacks = atmService.updateStacks(atm, newStacks, notes, 5);
        when(atmRepository.save(any())).thenReturn(atm);
        Float correctAtmMoney = Float.valueOf(830);
        assertEquals(java.util.Optional.of(correctAtmMoney), java.util.Optional.ofNullable(atmService.findTotalAtmMoney(atm)));

    }

    @Test
    void countMockWithdraw() {
        List<Stacks> newStacks = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        when(atmRepository.getById(any())).thenReturn(atm);
        atm.setStacks(newStacks);
        when(atmRepository.save(any())).thenReturn(atm);

        List<Notes> notes = new ArrayList<Notes>();
        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));

        Float soldTobeTested = Float.valueOf(830);

        newStacks = atmService.updateStacks(atm, newStacks, notes, 5);
        when(atmRepository.save(any())).thenReturn(atm);
        result = atmService.countWithdraw(atm, newStacks, soldTobeTested, 20);
        when(atmRepository.save(any())).thenReturn(atm);

        Assertions.assertThat(
                atm.getStacks().stream()
                        .anyMatch(stack -> "Leu_10".equals(stack.getNote().getType())
                                && stack.getNote().getValue() == 10
                                && stack.getCount() == 3)
        ).isTrue();



    }

    @Test
    public void showMockAtmBalance() {
        List<Stacks> newStacks = new ArrayList<>();
        List<Stacks> ballanceTest = new ArrayList<>();

        when(atmRepository.getById(any())).thenReturn(atm);
        atm.setStacks(newStacks);
        when(atmRepository.save(any())).thenReturn(atm);


        List<Notes> notes = new ArrayList<Notes>();
        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));

        newStacks = atmService.updateStacks(atm, newStacks, notes, 1);
        ballanceTest = atm.getStacks();
        when(atmRepository.save(any())).thenReturn(atm);
        Assertions.assertThat(
                ballanceTest.stream()
                        .anyMatch(stack -> "Leu_100".equals(stack.getNote().getType())
                                && stack.getNote().getValue() == 100
                                && stack.getCount() == 1)
        ).isTrue();
        // System.out.println(newStacks.toString());


    }

    @Test
    void updateNewStacks() {
        List<Stacks> newStacks = new ArrayList<>();
        when(atmRepository.getById(any())).thenReturn(atm);
        atm.setStacks(newStacks);
        when(atmRepository.save(any())).thenReturn(atm);

        List<Notes> notes = new ArrayList<Notes>();
        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));

        newStacks = atmService.updateStacks(atm, newStacks, notes, 1);
        Stacks stackTest = new Stacks(new Notes("Leu_100", 100), 1);

        when(atmRepository.save(any())).thenReturn(atm);

        Assertions.assertThat(
                atm.getStacks().stream()
                        .anyMatch(stack -> stackTest.getNote().getType().equals(stack.getNote().getType())
                                && stack.getNote().getValue() == 100
                                && stack.getCount() == 1)
        ).isTrue();
        // assertArrayEquals(message, newStacks.toArray(), new Object[]{atm.getStacks().toArray()});
        //System.out.println(newStacks.toString());


    }

}
