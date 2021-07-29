package com.atm_machine_web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AtmMachineWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testDivide(){
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(42,0);
        });
    }




}
