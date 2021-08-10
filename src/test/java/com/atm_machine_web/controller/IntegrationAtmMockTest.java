package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.AtmDTO;
import com.atm_machine_web.controller.dto.WithdrawDTO;
import com.atm_machine_web.controller.dto.WithdrawResponseDTO;
import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.service.AtmServiceTest;
import com.atm_machine_web.service_impl.AtmServiceImpl;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IntegrationAtmMockTest {
    @Mock
    AtmServiceTest atmServiceTest;
    ModelMapper modelMapper;
    List<Notes> notes;
    List<Stacks> mockStacks;
    @InjectMocks
    AtmServiceImpl atmService;
    @Spy
    Atm atm;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        notes = new ArrayList<>();
        mockStacks = new ArrayList<>();

        notes.add(new Notes("Leu_100", 100));
        notes.add(new Notes("Leu_50",
                50));
        notes.add(new Notes("Leu_10", 10));
        notes.add(new Notes("Leu_5", 5));
        notes.add(new Notes("Leu_1", 1));
        Integer nrNotes = 1;
        mockStacks.add(new Stacks(new Notes("Leu_100", 100), nrNotes));
        mockStacks.add(new Stacks(new Notes("Leu_50", 50), nrNotes));
        mockStacks.add(new Stacks(new Notes("Leu_10", 10), nrNotes));
        mockStacks.add(new Stacks(new Notes("Leu_5", 5), nrNotes));
        mockStacks.add(new Stacks(new Notes("Leu_1", 1), nrNotes));
        atm = new Atm(1L, (float) 0);
    }

    @Test
    public void getBalanceAtm() {
        RestAssured
                .get("http://localhost:8080/show_balance_atm")
                .then().statusCode(200);
    }

    @Test
    public void mockRefill() {
        List<Stacks> newStacks = new ArrayList<>();
        AtmDTO atmDto;
        atmDto = modelMapper.map(atm, AtmDTO.class);
        atmDto.setStacks(mockStacks);
        atmDto.setAtmMoney(1166.0F);

        AtmDTO response =
                RestAssured.given()
                        .contentType("application/json;")
                        .post("http://localhost:8080/refill_stacks_atm?nrNotes=1")
                        .then().statusCode(200)
                        .extract().as(AtmDTO.class);

        Atm actual = modelMapper.map(response, Atm.class);
        Atm expected = modelMapper.map(atmDto, Atm.class);
        Assertions.assertEquals(expected.getAtmId(), actual.getAtmId());
        //Assertions.assertEquals(expected.getAtmMoney(), actual.getAtmMoney());

    }

    @Test
    public void mockAfterRefillWithdraw() {

        AtmDTO atmDto;
        atmDto = modelMapper.map(atm, AtmDTO.class);
        atmDto.setStacks(mockStacks);
        atmDto.setAtmMoney(1166.0F);

        AtmDTO response =
                RestAssured.given()
                        .contentType("application/json;")
                        .post("http://localhost:8080/refill_stacks_atm?nrNotes=1")
                        .then().statusCode(200)
                        .extract().as(AtmDTO.class);

        Atm actual = modelMapper.map(response, Atm.class);
        String username = new String("Diana");
        String currency = new String("LEU");
        Integer sumToBeExtracted = 100;

        WithdrawDTO sendBody = modelMapper.map(actual, WithdrawDTO.class);
        sendBody.setSum(sumToBeExtracted);
        sendBody.setCurrency(currency);
        sendBody.setUsername(username);

        WithdrawResponseDTO responseWithdraw =
                RestAssured.given()
                        .body(sendBody)
                        .contentType("application/json;")
                        .post("/withdraw_from_atm")
                        .then().statusCode(200)
                        .extract().as(WithdrawResponseDTO.class);

        Float soldExpected = 800.0F;
        Assertions.assertEquals(responseWithdraw.getSold(), soldExpected);
    }
}
