package com.atm_machine_web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AtmMachineWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testDivide() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(42, 0);
        });
    }

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        //given
        int nrOne = 20;
        int nrTwo = 30;
        //when
        int result = underTest.add(nrOne, nrTwo);
        //then
        assertThat(result).isEqualTo(50);

    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}
